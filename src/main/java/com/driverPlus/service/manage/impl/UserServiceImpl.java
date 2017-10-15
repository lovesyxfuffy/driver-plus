package com.driverPlus.service.manage.impl;

import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.mapper.front.UserMapper;
import com.driverPlus.dao.mapper.manage.AgentMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.User;
import com.driverPlus.dao.po.front.UserExample;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.AgentExample;
import com.driverPlus.enums.AgentStatusEnum;
import com.driverPlus.service.manage.AgentService;
import com.driverPlus.service.manage.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserList(){
        UserExample example = new UserExample();
        UserExample.Criteria criteria=example.createCriteria();

        return userMapper.selectByExample(example);
    }
    @Override
    public Map<Integer,User> getAllUserMapById(){
        Map<Integer,User> userHashMap=new HashMap<>();
        List<User> userList=getUserList();
        for(User user:userList){
            userHashMap.put(user.getId(),user);
        }
        return userHashMap;
    }


}
