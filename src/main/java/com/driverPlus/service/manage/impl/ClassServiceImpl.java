package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.manage.ClassMapper;
import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.dao.po.manage.ClassExample;
import com.driverPlus.service.manage.ClassService;
import com.driverPlus.Auth.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;

    @Override
    public List<Class> getClassList() {
        ClassExample example = new ClassExample();
        ClassExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());

        return classMapper.selectByExample(example);
    }
    @Override
    public Map<Integer,Class> getAllClassMap(){
        Map<Integer,Class> classHashMap=new HashMap<>();
        List<Class> classList=getClassList();
        for(Class classl:classList){
            classHashMap.put(classl.getId(),classl);
        }
        return classHashMap;
    }
}
