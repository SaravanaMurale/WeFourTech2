package com.pojo.wefourtech.menuoperation;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.pojo.wefourtech.R;
import com.pojo.wefourtech.utils.PermissionUtils;

import static com.pojo.wefourtech.utils.AppConstant.LOCATION_PERMISSION_REQUEST_CODE;

public class MainActivity extends AppCompatActivity {

    Button btnGetLocation,btnAddForm,viewForm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetLocation=(Button)findViewById(R.id.getLocation);
        btnAddForm=(Button)findViewById(R.id.addForm);
        viewForm=(Button)findViewById(R.id.viewDetails);

        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!PermissionUtils.hasPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        && !PermissionUtils.hasPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                    PermissionUtils.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

                }

                else {

                    if (PermissionUtils.hasPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                            && PermissionUtils.hasPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {


                        Intent intent=new Intent(MainActivity.this,MapActivity.class);
                        startActivity(intent);



                    }

                }


            }
        });

        btnAddForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}