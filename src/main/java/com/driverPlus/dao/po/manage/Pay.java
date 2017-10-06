package com.driverPlus.dao.po.manage;

import java.util.Date;

public class Pay {
    private Integer id;

    private Integer orderId;

    private Integer payCount;

    private String payWay;

    private String payFor;

    private Date addTime;

    private Integer schoolId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getPayFor() {
        return payFor;
    }

    public void setPayFor(String payFor) {
        this.payFor = payFor == null ? null : payFor.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}