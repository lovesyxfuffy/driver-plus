package com.driverPlus.dao.po.front;

import java.util.Date;

public class Notice {
    private Integer id;

    private Integer forUserId;

    private String content;

    private String name;

    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForUserId() {
        return forUserId;
    }

    public void setForUserId(Integer forUserId) {
        this.forUserId = forUserId;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}