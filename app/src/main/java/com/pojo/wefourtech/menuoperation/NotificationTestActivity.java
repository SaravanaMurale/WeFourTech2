package com.pojo.wefourtech.menuoperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.pojo.wefourtech.R;
import com.pojo.wefourtech.utils.NotificationReceiver;

public class NotificationTestActivity extends AppCompatActivity {

    Button notiTest;

    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);

        notiTest=(Button)findViewById(R.id.notiTest);

        notificationManagerCompat=NotificationManagerCompat.from(this);

        notiTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tit="Title";
                String body="Body";

                Intent intent=new Intent(NotificationTestActivity.this, NotificationReceiver.class);
                intent.putExtra("MESSAGE",body);


                PendingIntent pendingIntent=PendingIntent.getBroadcast(NotificationTestActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification=new NotificationCompat.Builder(NotificationTestActivity.this,"1")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(tit)
                        .setContentText(body)
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();

                notificationManagerCompat.notify(1,notification);


               /* new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent openIntent=new Intent(NotificationTestActivity.this, MainActivity.class);
                        startActivity(openIntent);

                    }
                },2000);
*/



            }
        });
    }
}