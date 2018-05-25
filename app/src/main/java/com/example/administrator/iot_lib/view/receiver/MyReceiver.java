package com.example.administrator.iot_lib.view.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.example.administrator.iot_lib.view.trace.Trace;



public class MyReceiver extends BroadcastReceiver{

    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Trace.d(TAG,"    public void onReceive(Context context, Intent intent) {");
        Toast.makeText(context,Thread.currentThread().getName(),Toast.LENGTH_LONG).show();
    }
}
