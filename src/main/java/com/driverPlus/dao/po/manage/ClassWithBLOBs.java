package com.driverPlus.dao.po.manage;

public class ClassWithBLOBs extends Class {
    private String priceContent;

    private String servicePromise;

    public String getPriceContent() {
        return priceContent;
    }

    public void setPriceContent(String priceContent) {
        this.priceContent = priceContent == null ? null : priceContent.trim();
    }

    public String getServicePromise() {
        return servicePromise;
    }

    public void setServicePromise(String servicePromise) {
        this.servicePromise = servicePromise == null ? null : servicePromise.trim();
    }
}