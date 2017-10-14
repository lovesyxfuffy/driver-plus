package com.driverPlus.dao.dto.manage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfeng on 17/10/11.
 */
public class NoticeDto implements Serializable {

    private List<Integer> idList;

    private Integer id;

    private Integer forUserId;

    private String forUserName;

    private Integer forShcoolId;

    private String forSchoolName;

    private Integer accountId;

    private String accountName;

    private String name;

    private String content;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForUserId() {
        return forUserId;
    }

    public void setForUserId(Integer forUserId) {
        this.forUserId = forUserId;
    }

    public String getForUserName() {
        return forUserName;
    }

    public void setForUserName(String forUserName) {
        this.forUserName = forUserName;
    }

    public Integer getForShcoolId() {
        return forShcoolId;
    }

    public void setForShcoolId(Integer forShcoolId) {
        this.forShcoolId = forShcoolId;
    }

    public String getForSchoolName() {
        return forSchoolName;
    }

    public void setForSchoolName(String forSchoolName) {
        this.forSchoolName = forSchoolName;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

}
