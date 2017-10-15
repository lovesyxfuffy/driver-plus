package com.driverPlus.dao.dto.manage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfeng on 17/10/11.
 */
public class ContestStatusDto implements Serializable {

    private List<Integer> idList;

    private Integer status;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
