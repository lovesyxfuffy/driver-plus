package com.driverPlus.dao.dto.manage;

import java.io.Serializable;

/**
 * Created by wangfeng on 17/10/11.
 */
public class StudentResultDto implements Serializable {

    private Integer id;
    private String realName;
    private String idcard;
    private String telephone;
    private String classTypeStr;
    private Integer price;
    private String addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getClassTypeStr() {
        return classTypeStr;
    }

    public void setClassTypeStr(String classTypeStr) {
        this.classTypeStr = classTypeStr;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

}
