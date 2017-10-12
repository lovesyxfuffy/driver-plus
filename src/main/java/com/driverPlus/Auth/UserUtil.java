package com.driverPlus.Auth;

import com.driverPlus.dao.po.UserInfo;

/**
 * Created by yujingyang on 2017/10/10.
 */
public class UserUtil {

    public static UserInfo userInfo;

    static {
        userInfo = new UserInfo();
        userInfo.setSchoolId(1);
        userInfo.setRoleId(0);
        userInfo.setAccountId(0);
    }

    public static Integer getSchoolId(){
        return userInfo.getSchoolId();
    }

    public static Integer getAccountId(){
        return userInfo.getAccountId();
    }

    public static UserInfo getUserInfo(){
        return userInfo;
    }

}
