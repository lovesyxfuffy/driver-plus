package com.driverPlus.dao.dto.manage;

import java.io.Serializable;

/**
 * Created by wangfeng on 17/10/11.
 */
public class SchoolResultDto implements Serializable {

    private Integer id;
    private String name;
    private String adminName;
    private String adminEmail;
    private String adminJob;
    private String telephone;
    private Integer smsCount;

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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminJob() {
        return adminJob;
    }

    public void setAdminJob(String adminJob) {
        this.adminJob = adminJob;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(Integer smsCount) {
        this.smsCount = smsCount;
    }

}
