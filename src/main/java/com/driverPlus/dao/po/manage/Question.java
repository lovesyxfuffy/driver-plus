package com.driverPlus.dao.po.manage;

import java.util.Date;

public class Question {
    private Integer id;

    private Integer type;

    private String content;

    private Integer chapter;

    private Date addTime;

    private String explaination;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getChapter() {
        return chapter;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination == null ? null : explaination.trim();
    }
}