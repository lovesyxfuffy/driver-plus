package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.manage.FieldMapper;
import com.driverPlus.dao.po.manage.Field;
import com.driverPlus.dao.po.manage.FieldExample;
import com.driverPlus.service.manage.FieldService;
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
public class FieldServiceImpl implements FieldService {

    @Autowired
    FieldMapper fieldMapper;

    @Override
    public List<Field> getFieldList() {
        FieldExample example = new FieldExample();
        FieldExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());

        return fieldMapper.selectByExample(example);
    }
    @Override
    public Map<Integer,Field> getAllFieldMap(){
        Map<Integer,Field> fieldHashMap=new HashMap<>();
        List<Field> fieldList=getFieldList();
        for(Field field:fieldList){
            fieldHashMap.put(field.getId(),field);
        }
        return fieldHashMap;
    }
}
