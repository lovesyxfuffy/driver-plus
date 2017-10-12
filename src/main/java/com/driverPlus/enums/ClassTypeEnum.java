package com.driverPlus.enums;

/**
 * Created by wangfeng on 2017/10/11.
 */
public enum ClassTypeEnum {
    A1("A1", "A1"),
    A2("A2", "A2"),
    A3("A3", "A3"),
    B1("B1", "B1"),
    B2("B2", "B2"),
    C1("C1", "C1"),
    C2("C2", "C2"),
    C3("C3", "C3"),
    C4("C4", "C4"),
    D("D", "D"),
    E("E", "E"),
    F("F", "F"),
    M("M", "M"),
    N("N", "N"),
    P("P", "P");

    private String code;

    private String name;

    ClassTypeEnum(String code, String name) {
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

    public static ClassTypeEnum getByCode(String code){
        for(ClassTypeEnum item: ClassTypeEnum.values()){
            if(code==item.getCode()){
                return item;
            }
        }
        return null;
    }
}
