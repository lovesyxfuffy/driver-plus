package com.driverPlus.dao.po.manage;

import java.util.Date;

public class Agent {
    private Integer id;

    private String realName;

    private String telephone;

    private Integer studentCount;

    private Integer reduction;

    private Integer profitShare;

    private Date addTime;

    private Date updateTime;

    private Integer schoolId;

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
        this.realName = realName == null ? null : realName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Integer getReduction() {
        return reduction;
    }

    public void setReduction(Integer reduction) {
        this.reduction = reduction;
    }

    public Integer getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(Integer profitShare) {
        this.profitShare = profitShare;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}