package com.driverPlus.enums;

/**
 * Created by wangfeng on 2017/10/7.
 */
public enum SchoolStatusEnum {
    signIn(0, "注册成功"),
    pay(1, "支付完成"),
    agree(2,"审核通过"),
    offlin(3,"下线成功"),
    refuse(4,"审核不通过");

    private int code;

    private String name;

    SchoolStatusEnum(int code, String name) {
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
