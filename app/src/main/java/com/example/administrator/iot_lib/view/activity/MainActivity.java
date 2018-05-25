package com.example.administrator.iot_lib.view.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.iot_lib.R;
import com.example.administrator.iot_lib.view.activity.code.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent = new Intent("ELITOR_CLOCK");
        intent.putExtra("msg","你该打酱油了");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
        am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),1000,pendingIntent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


}
