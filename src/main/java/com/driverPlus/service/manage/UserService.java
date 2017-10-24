package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.User;
import com.driverPlus.dao.po.manage.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface UserService {

    List<User> getUserList();
    Map<Integer,User> getAllUserMapById();
    List<User>getUserListByPhone(String telephone);

}
