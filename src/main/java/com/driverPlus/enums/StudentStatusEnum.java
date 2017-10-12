package com.driverPlus.enums;

/**
 * Created by wangfeng on 2017/10/7.
 */
public enum StudentStatusEnum {//// TODO: 17/10/12
    unpass(0, "不通过"),
    pass(1, "通过");

    private Integer code;

    private String name;

    StudentStatusEnum(Integer code, String name) {
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

    public static StudentStatusEnum getByCode(Integer code){
        for(StudentStatusEnum item: StudentStatusEnum.values()){
            if(code==item.getCode()){
                return item;
            }
        }
        return null;
    }
}
