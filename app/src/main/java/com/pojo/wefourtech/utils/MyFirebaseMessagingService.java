package com.pojo.wefourtech.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pojo.wefourtech.R;
import com.pojo.wefourtech.menuoperation.NotificationActivity;
import com.pojo.wefourtech.menuoperation.NotificationTestActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    Context context;

    public MyFirebaseMessagingService() {
    }

    public MyFirebaseMessagingService(Context context) {

        this.context=context;

    }

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

        Intent intent = new Intent(MyFirebaseMessagingService.this, NotificationActivity.class);//here put your activity name which you want to open
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void getFirebaseMessage(String title, String body) {

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        Intent intent=new Intent(this, NotificationActivity.class);
        intent.putExtra("MESSAGE",body);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

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
