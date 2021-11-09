package com.pojo.wefourtech.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.pojo.wefourtech.menuoperation.MainActivity;

public class NotificationReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("MESSAGE");

        System.out.println("ReceivedMessage"+msg);

        if(msg!=null){
            Intent intent1=new Intent(context, MainActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }

        //System.out.println("ReceivedIntent "+intent.getAction());

       /* if(intent.getAction().equals("com.google.firebase.MESSAGING_EVENT")){
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
            System.out.println("NotificationRecived");
        }
*/


    }
}
