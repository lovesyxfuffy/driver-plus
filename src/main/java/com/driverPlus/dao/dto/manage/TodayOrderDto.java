package com.driverPlus.dao.dto.manage;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

/**
 * Created by wangfeng on 17/10/11.
 */
public class TodayOrderDto implements Serializable {

    private Integer orderCountToday;
    private Integer orderConfirmed;
    private Integer orderWaitForConfirmed;
    private Integer orderCanceled;
    private Float payAmount;
    private Float onlinePayAmount;
    private Float offlinePayAmount;

    public Integer getOrderCountToday() {
        return orderCountToday;
    }

    public void setOrderCountToday(Integer orderCountToday) {
        this.orderCountToday = orderCountToday;
    }

    public Integer getOrderConfirmed() {
        return orderConfirmed;
    }

    public void setOrderConfirmed(Integer orderConfirmed) {
        this.orderConfirmed = orderConfirmed;
    }

    public Integer getOrderWaitForConfirmed() {
        return orderWaitForConfirmed;
    }

    public void setOrderWaitForConfirmed(Integer orderWaitForConfirmed) {
        this.orderWaitForConfirmed = orderWaitForConfirmed;
    }

    public Integer getOrderCanceled() {
        return orderCanceled;
    }

    public void setOrderCanceled(Integer orderCanceled) {
        this.orderCanceled = orderCanceled;
    }

    public Float getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Float payAmount) {
        this.payAmount = payAmount;
    }

    public Float getOnlinePayAmount() {
        return onlinePayAmount;
    }

    public void setOnlinePayAmount(Float onlinePayAmount) {
        this.onlinePayAmount = onlinePayAmount;
    }

    public Float getOfflinePayAmount() {
        return offlinePayAmount;
    }

    public void setOfflinePayAmount(Float offlinePayAmount) {
        this.offlinePayAmount = offlinePayAmount;
    }

}
