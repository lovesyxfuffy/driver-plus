package com.driverPlus.dao.dto.manage;

import java.io.Serializable;

/**
 * Created by wangfeng on 17/10/11.
 */
public class QuerySchoolParam implements Serializable {

    private String city;
    private Integer pageNo;
    private Integer pageSize;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
