package com.example.administrator.iot_lib.view.engine;

/**
 * OTA同步请求
 */
public class OTAExecuteManager {
    private static OTAExecuteManager mInstance;
    private static final String TAG = "OTAExecuteManager";

    private OTAExecuteManager(){
    }

    public static OTAExecuteManager getInstance(){
        if(mInstance == null){
            synchronized (OTAExecuteManager.class){
                if (mInstance == null){
                    mInstance = new OTAExecuteManager();
                }
            }
        }
        return mInstance;
    }

    public String checkVersion(){
        return null;
    }
}
