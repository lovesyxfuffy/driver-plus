package com.driverPlus.enums;

/**
 * Created by wangfeng on 2017/10/11.
 */
public enum PayWayEnum {
    offline("offline", "线下支付"),
    wechat("wechat", "微信支付"),
    alipay("alipay", "支付宝支付");

    private String code;

    private String name;

    PayWayEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
