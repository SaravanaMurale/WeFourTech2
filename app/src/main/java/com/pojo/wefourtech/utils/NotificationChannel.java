package com.pojo.wefourtech.utils;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.Toast;

public class NotificationChannel extends Application {


    public static final String channel1="1";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {

            android.app.NotificationChannel notificationChannel1 = new android.app.NotificationChannel(
                    channel1, "My Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel1.setDescription("Channel 1 Description");
            notificationChannel1.setShowBadge(true);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel1);


        }
        else {
            Toast.makeText(getApplicationContext(),"Android Version is Below Oreo",Toast.LENGTH_LONG).show();
            System.out.println("Android Version is Below Ores");
        }




    }
}
