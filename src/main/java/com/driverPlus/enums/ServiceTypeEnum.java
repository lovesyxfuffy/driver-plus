package com.driverPlus.enums;

/**
 * Created by wangfeng on 2017/10/11.
 */
public enum ServiceTypeEnum {
    sms_service(1, "短信服务"),
    service(2, "普通服务");

    private Integer code;

    private String name;

    ServiceTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ServiceTypeEnum getByCode(Integer code){
        for(ServiceTypeEnum item: ServiceTypeEnum.values()){
            if(code==item.getCode()){
                return item;
            }
        }
        return null;
    }
}
