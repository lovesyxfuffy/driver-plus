package com.driverPlus.enums;

/**
 * Created by wangfeng on 2017/10/11.
 */
public enum OrderStatusEnum {
    submit(0, "已提交订单"),
    paying(1, "支付中"),
    paid(2,"支付完成"),
    confirmed(3,"确认完成"),
    canceled(-3,"取消订单");

    private int code;

    private String name;

    OrderStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
