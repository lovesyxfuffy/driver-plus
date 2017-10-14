package com.driverPlus.enums;

/**
 * Created by wangfeng on 2017/10/11.
 */
public enum AgentStatusEnum {
    init(1, "新建"),
    closed(0, "禁用");

    private Integer code;

    private String name;

    AgentStatusEnum(Integer code, String name) {
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

    public static AgentStatusEnum getByCode(Integer code){
        for(AgentStatusEnum item: AgentStatusEnum.values()){
            if(code==item.getCode()){
                return item;
            }
        }
        return null;
    }
}
