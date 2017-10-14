package com.driverPlus.Auth;

import com.driverPlus.dao.po.WechatUserInfo;

/**
 * Created by yujingyang on 2017/10/12.
 */
public class WechatUserUtil {
    private static WechatUserInfo wechatUserInfo = new WechatUserInfo();

    static {
        wechatUserInfo.setSchoolId(1);
        wechatUserInfo.setSchoolId(1);
    }

    public static WechatUserInfo getWechatUserInfo() {
        return wechatUserInfo;
    }

    public static Integer getUserId(){
        return wechatUserInfo.getUserId();
    }

    public static Integer getSchoolId(){
        return wechatUserInfo.getSchoolId();
    }
}
