package com.example.administrator.iot_lib.view.info;

import java.util.HashMap;
import java.util.List;

public class VersionInfo {
    private static VersionInfo mInstance;
    private VersionInfo(){

    }
    public static VersionInfo getInstance(){
        if (mInstance == null) {
            synchronized (VersionInfo.class) {
                mInstance = new VersionInfo();
            }
        }
        return mInstance;
    }

    /**
     * 策略信息map
     */
    public HashMap<String, PolicyMapInfo> policyHashMap = new HashMap<String, PolicyMapInfo>();

    /**
     * 升级版本号
     */
    public String versionName;
    /**
     * 升级包大小
     */
    public long fileSize;
    /**
     * 升级包ID
     */
    public String deltaID;
    /**
     * 升级包MD5检验值，校验升级包是否下载正确
     */
    public String md5sum;
    /**
     * 升级包下载地址
     */
    public String deltaUrl;

    /**
     * 发布日期
     */
    public String publishDate;
    /**
     * 日志内容
     */
    public String content;

    /**
     * 分段下载
     */
    public List<SegmentDownInfo> segmentSha;

    /**
     * 版本别名
     */
    public String versionAlias;

    @Override
    public String toString() {
        return "VersionInfo{" + "\n" +
                "versionName='" + versionName + '\'' + "\n" +
                "versionAlias='" + versionAlias + '\'' + "\n" +
                "fileSize=" + fileSize + "\n" +
                "deltaID='" + deltaID + '\'' + "\n" +
                "md5sum='" + md5sum + '\'' + "\n" +
                "deltaUrl='" + deltaUrl + '\'' + "\n" +
                "publishDate='" + publishDate + '\'' + "\n" +
                "content='" + content + '\'' + "\n" +
                '}';
    }

    /**
     * 数据重置
     */
    public void reset() {
        policyHashMap.clear();
        versionName = null;
        versionAlias = null;
        fileSize = 0;
        deltaID = null;
        md5sum = null;
        deltaUrl = null;
        publishDate = null;
        content = null;
        segmentSha = null;
    }

}
