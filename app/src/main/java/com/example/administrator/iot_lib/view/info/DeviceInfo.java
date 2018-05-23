package com.example.administrator.iot_lib.view.info;

public class DeviceInfo {
    private static DeviceInfo mdeviceInfo;
    public String mid;
    public String version;
    public String oem;
    public String models;
    public String platform;
    public String deviceType;
    public String requestPush;
    private static final String KEY_DEVICE_INFO = "key_device_info";

    private DeviceInfo(){

    }
    public static DeviceInfo getInstance(){
        if (mdeviceInfo == null){
            synchronized (DeviceInfo.class){
                if (mdeviceInfo == null){
                    mdeviceInfo = new DeviceInfo();
                }
            }
        }
        return mdeviceInfo;
    }

    public void init(){

    }




}
