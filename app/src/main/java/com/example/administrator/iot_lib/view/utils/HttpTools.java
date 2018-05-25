package com.example.administrator.iot_lib.view.utils;

import android.content.Context;

import com.example.administrator.iot_lib.view.info.DeviceInfo;

public class HttpTools {
    private static final String TAG = "HttpTools";
    private static HttpTools mInstance;

    private HttpTools(){
    }

    public static HttpTools getInstance(){
        if (mInstance == null){
            synchronized (HttpTools.class){
                if (mInstance == null){
                    mInstance = new HttpTools();
                }
            }
        }
        return mInstance;
    }

    /**
     * 注册，获取连接时的账号，密码
     *
     * @param deviceInfo
     * @param context
     * @return
     */
//    public String doPostRegister(DeviceInfo deviceInfo, Context context){
//        String appVersion = "";
//
//    }
}
