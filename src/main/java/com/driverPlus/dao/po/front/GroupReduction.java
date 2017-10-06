package com.driverPlus.dao.po.front;

import java.util.Date;

public class GroupReduction {
    private Integer id;

    private Integer groupId;

    private Integer studentCount;

    private Integer reduction;

    private Date addTime;

    private Integer schoolId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Integer getReduction() {
        return reduction;
    }

    public void setReduction(Integer reduction) {
        this.reduction = reduction;
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