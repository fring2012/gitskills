package com.example.administrator.iot_lib.view;

import android.content.Context;

import com.example.administrator.iot_lib.view.info.CustomDeviceInfo;
import com.example.administrator.iot_lib.view.trace.Trace;
import com.example.administrator.iot_lib.view.utils.FileUtil;

import java.io.File;


public class OtaAgentPolicy {
    public static final String TAG = "OtaAgentPolicy";
    public static Context sCx;

    public static Builder config;


    public static class Builder{
        /**
         * 下载路径
         */
        public String updatePath;

        /**
         * 日志文件存放路径
         */
        public String tracePath;
        /**
         * 是否打印日志
         */
        public boolean showTrace = true;
        /**
         * 自定义设备信息
         */
        public CustomDeviceInfo customDeviceInfo;
        /**
         * 设备为唯一标识吗
         */
        public String mid;
        /**
         * 是否上报错误日志
         */
        public boolean reportLog = true;

        /**
         * 设置升级包下载路径
         *
         * @param path
         * @return {@link Builder}
         */
        public Builder setUpdatePath(String path) {
            Trace.d(TAG, "setUpdatePath() :" + path);
            boolean existsDir = FileUtil.createOrExistsDir(new File(path).getParentFile().getAbsolutePath());
            if (existsDir) {
                updatePath = path;
            } else {
                Trace.e(TAG, "setUpdatePath() path is invalid ! set path fail");
            }
            return this;
        }
    }
}
