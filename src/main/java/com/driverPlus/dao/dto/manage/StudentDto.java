package com.driverPlus.dao.dto.manage;

import java.io.Serializable;

/**
 * Created by wangfeng on 17/10/11.
 */
public class StudentDto implements Serializable {

    private Integer id;
    private String name;
    private String idcard;
    private String telephone;
    private String classTypeStr;
    private String statusStr;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
