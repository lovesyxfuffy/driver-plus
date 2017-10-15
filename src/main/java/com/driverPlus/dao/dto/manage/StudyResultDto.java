package com.driverPlus.dao.dto.manage;

import java.io.Serializable;

/**
 * Created by wangfeng on 17/10/11.
 */
public class StudyResultDto implements Serializable {

    private Integer id;

    private Integer condition1;

    private Integer condition2;

    private Integer condition3;

    private Integer contestResult;

    private String name;

    private String telephone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCondition1() {
        return condition1;
    }

    public void setCondition1(Integer condition1) {
        this.condition1 = condition1;
    }

    public Integer getCondition2() {
        return condition2;
    }

    public void setCondition2(Integer condition2) {
        this.condition2 = condition2;
    }

    public Integer getCondition3() {
        return condition3;
    }

    public void setCondition3(Integer condition3) {
        this.condition3 = condition3;
    }

    public Integer getContestResult() {
        return contestResult;
    }

    public void setContestResult(Integer contestResult) {
        this.contestResult = contestResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
