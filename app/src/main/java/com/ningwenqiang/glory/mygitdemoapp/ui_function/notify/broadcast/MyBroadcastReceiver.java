package com.ningwenqiang.glory.mygitdemoapp.ui_function.notify.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * create by: 86136
 * create time: 2020/10/26 16:43
 * Function description:
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到广播了", Toast.LENGTH_LONG).show();
    }
}
