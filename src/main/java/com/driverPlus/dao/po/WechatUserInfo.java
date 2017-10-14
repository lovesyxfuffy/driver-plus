package com.driverPlus.dao.po;

import lombok.Data;

/**
 * Created by yujingyang on 2017/10/12.
 */
@Data
public class WechatUserInfo {
    private Integer userId;
    private String openId;
    private Integer schoolId;
    private String sessionKey;

}
