package com.pojo.wefourtech.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("MESSAGE");

        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();

    }
}
