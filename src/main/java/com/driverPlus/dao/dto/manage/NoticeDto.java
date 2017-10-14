package com.driverPlus.dao.dto.manage;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangfeng on 17/10/11.
 */
public class NoticeDto implements Serializable {

    private String name;

    private String content;

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


}
