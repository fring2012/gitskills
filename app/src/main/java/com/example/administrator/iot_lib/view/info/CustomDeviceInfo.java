package com.example.administrator.iot_lib.view.info;

public class CustomDeviceInfo {
    /**
     * 设备唯一标识码
     */
    public String mid;
    /**
     * 项目唯一标识码
     */
    public String productId;

    /**
     * 项目加密码
     */
    public String product_secret;

    /**
     * 设备版本号
     */
    public String version;
    /**
     * 厂商信息，广升提供
     */
    public String oem;
    /**
     * 设备型号，同一个厂商下面不允许出现相同型号的设备。oem+ models组成一个项目
     */
    public String models;
    /**
     * 芯片平台信息，如MTK6582、SPRD8830、MSM9x15，广升给出平台列表
     */
    public String platform;
    /**
     * 设备类型，如phone、box、pad、mifi等，广升给出类型列表
     */
    public String deviceType;
    /**
     * 是否开启升级推送
     */
    public String requestPush;


    public CustomDeviceInfo setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public CustomDeviceInfo setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public CustomDeviceInfo setProduct_secret(String product_secret) {
        this.product_secret = product_secret;
        return this;
    }

    public CustomDeviceInfo setVersion(String version) {
        this.version = version;
        return this;
    }

    public CustomDeviceInfo setOem(String oem) {
        this.oem = oem;
        return this;
    }

    public CustomDeviceInfo setModels(String models) {
        this.models = models;
        return this;
    }

    public CustomDeviceInfo setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public CustomDeviceInfo setDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public CustomDeviceInfo setRequestPush(String requestPush) {
        this.requestPush = requestPush;
        return this;
    }
}
