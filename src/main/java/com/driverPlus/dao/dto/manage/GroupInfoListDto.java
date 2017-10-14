package com.driverPlus.dao.dto.manage;

import com.driverPlus.dao.po.front.GroupReduction;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangfeng on 17/10/14.
 */
public class GroupInfoListDto implements Serializable {

    private int deadline;
    private String strategy;
    private String content;
    private Integer studentCount;
    private Integer groupCount;
    private List<GroupReduction> reductionList;

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public List<GroupReduction> getReductionList() {
        return reductionList;
    }

    public void setReductionList(List<GroupReduction> reductionList) {
        this.reductionList = reductionList;
    }

}
