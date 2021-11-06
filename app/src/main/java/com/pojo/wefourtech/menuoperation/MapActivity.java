package com.pojo.wefourtech.menuoperation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.pojo.wefourtech.R;
import com.pojo.wefourtech.utils.GpsUtils;

import java.util.List;

import static com.pojo.wefourtech.utils.AppConstant.GPS_PROVIDER_CODE;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private View mapView;
    private boolean gpsEnabledStatus;
    private boolean isGPS;
    FusedLocationProviderClient mFusedLocationProviderClient;
    public LocationManager locationManager;
    private Location mLastKnownLocation;
    Double myLocationLat, myLocationLon;
    private LocationCallback locationCallback;

    private final float DEFAULT_ZOOM = 18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        enableGps();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapView = mapFragment.getView();

        mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(MapActivity.this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



    }

    private void enableGps() {


        gpsEnabledStatus = new GpsUtils(MapActivity.this).gpsStatus();

        if (gpsEnabledStatus) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getDeviceLocation();
                }
            }, 1000);

        } else if (!gpsEnabledStatus) {
            System.out.println("IamcalledEnalbleGPS");
            enableGPS();
        }



    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {

        mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                if (task.isSuccessful()) {
                    mLastKnownLocation = task.getResult();
                    if (mLastKnownLocation != null) {

                        myLocationLat = mLastKnownLocation.getLatitude();
                        myLocationLon = mLastKnownLocation.getLongitude();

                        System.out.println("LATITIDEANDLONGITITE " + myLocationLat + " " + myLocationLon);

                        getAddressFromLatiandLongi(myLocationLat, myLocationLon);

                        moveCamera(myLocationLat, myLocationLon);



                    } else if (mLastKnownLocation == null) {

                        final LocationRequest locationRequest = LocationRequest.create();
                        locationRequest.setInterval(10000);
                        locationRequest.setFastestInterval(5000);
                        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                        locationCallback = new LocationCallback() {

                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                super.onLocationResult(locationResult);

                                mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

                                if (locationResult == null) {
                                    return;
                                } else if (locationResult != null) {

                                    mLastKnownLocation = locationResult.getLastLocation();

                                    myLocationLat = mLastKnownLocation.getLatitude();
                                    myLocationLon = mLastKnownLocation.getLongitude();

                                    System.out.println("MyLocationLatLong" + myLocationLat + " " + myLocationLon);

                                    getAddressFromLatiandLongi(myLocationLat, myLocationLon);

                                    moveCamera(myLocationLat, myLocationLon);

                                    mFusedLocationProviderClient.removeLocationUpdates(locationCallback);

                                }


                            }
                        };
                    }

                }


            }
        });


    }

    private void moveCamera(Double myLocationLat, Double myLocationLon) {

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocationLat, myLocationLon), DEFAULT_ZOOM));
    }

    private void getAddressFromLatiandLongi(Double myLocationLat, Double myLocationLon) {

        List<Address> geoAddresses = GpsUtils.getAddressFromMap(MapActivity.this, myLocationLat, myLocationLon);

        if (geoAddresses.size() != 0) {

            String address = geoAddresses.get(0).getAddressLine(0);
            String area = geoAddresses.get(0).getLocality();
            String city = geoAddresses.get(0).getAdminArea();
            String country = geoAddresses.get(0).getCountryName();
            String postalCode = geoAddresses.get(0).getPostalCode();
            String subAdminArea = geoAddresses.get(0).getSubAdminArea();
            String subLocality = geoAddresses.get(0).getSubLocality();
            String premises = geoAddresses.get(0).getPremises();
            String addressLine = geoAddresses.get(0).getAddressLine(0);

            System.out.println("MyAddress" + address + " " + area + " " + city + " " + postalCode);

            //login_checkin_location.setText(address + " " + area + " " + city + " " + postalCode);


        } else {

            Toast.makeText(MapActivity.this, "Please try again", Toast.LENGTH_LONG).show();
        }

    }

    private void enableGPS() {

        new GpsUtils(MapActivity.this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS*

                isGPS = isGPSEnable;

                System.out.println("ISGPS" + isGPS);

            }
        });

    }


    @Override
    public void
    onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_PROVIDER_CODE) {

                isGPS = true;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDeviceLocation();
                    }
                }, 1000);

            }

        } else {

        }
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }


}