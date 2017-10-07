package com.driverPlus.dao.po.manage;

import java.util.Date;

public class Notice {
    private Integer id;

    private Integer forSchoolId;

    private String content;

    private String name;

    private Integer accountId;

    private Date addTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForSchoolId() {
        return forSchoolId;
    }

    public void setForSchoolId(Integer forSchoolId) {
        this.forSchoolId = forSchoolId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}