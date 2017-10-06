package com.driverPlus.dao.po.manage;

public class ClassWithBLOBs extends Class {
    private String servicePromise;

    private String priceContent;

    public String getServicePromise() {
        return servicePromise;
    }

    public void setServicePromise(String servicePromise) {
        this.servicePromise = servicePromise == null ? null : servicePromise.trim();
    }

    public String getPriceContent() {
        return priceContent;
    }

    public void setPriceContent(String priceContent) {
        this.priceContent = priceContent == null ? null : priceContent.trim();
    }
}