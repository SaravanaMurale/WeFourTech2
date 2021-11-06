package com.pojo.wefourtech.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pojo.wefourtech.R;
import com.pojo.wefourtech.menuoperation.NotificationActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        System.out.println("FCMToken" + s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        System.out.println("Title" + remoteMessage.getNotification().getTitle() + " " + "Body" + remoteMessage.getNotification().getBody());

        getFirebaseMessage(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    private void getFirebaseMessage(String title, String body) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel1 = new NotificationChannel(
                    "1", "My Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel1.setDescription("Channel 1 Description");
            notificationChannel1.setShowBadge(true);

        }

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        /*NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"mychannel1")
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true);


        NotificationManagerCompat compat=NotificationManagerCompat.from(this);
        compat.notify(101,builder.build());*/

        Intent intent=new Intent(this,NotificationReceiver.class);
        intent.putExtra("MESSAGE",body);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification=new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1,notification);

    }

}
