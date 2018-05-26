package com.example.administrator.iot_lib.view.engine;

import android.os.Handler;

import com.example.administrator.iot_lib.view.engine.listener.ICheckVersionCallback;
import com.example.administrator.iot_lib.view.engine.listener.IDownloadListener;

public class IOTCallbackManager {
    private static final String TAG = "IOTCallbackManager";
    private Handler handler;
    Object lock = new Object();
    private static IOTCallbackManager mInstance;

    private static IDownloadListener iDownloadListener;
    private static ICheckVersionCallback iCheckVersionCallback;
}
