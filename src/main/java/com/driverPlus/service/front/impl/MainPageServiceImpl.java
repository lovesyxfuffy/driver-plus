package com.driverPlus.service.front.impl;

import com.driverPlus.dao.mapper.manage.ClassMapper;
import com.driverPlus.dao.mapper.manage.ConfigMapper;
import com.driverPlus.dao.mapper.manage.FieldMapper;
import com.driverPlus.dao.po.manage.*;
import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.service.front.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yujingyang on 2017/10/12.
 */
@Service
public class MainPageServiceImpl implements MainPageService {
    @Autowired
    ClassMapper classMapper;
    @Autowired
    ConfigMapper configMapper;
    @Autowired
    FieldMapper fieldMapper;

    @Override
    public List<Class> getClassList(Integer schoolId){
        ClassExample classExample = new ClassExample();
        classExample.createCriteria().andSchoolIdEqualTo(schoolId);

        return classMapper.selectByExample(classExample);

    }

    @Override
    public List<Config> getConfigList(Integer schoolId){
        ConfigExample configExample = new ConfigExample();
        configExample.createCriteria().andSchoolIdEqualTo(schoolId).andTypeEqualTo("wechat-main");
        return configMapper.selectByExample(configExample);
    }

    @Override
    public List<Field> getFieldList(Integer schoolId){
        FieldExample fieldExample = new FieldExample();
        fieldExample.createCriteria().andSchoolIdEqualTo(schoolId);

        return fieldMapper.selectByExample(fieldExample);
    }
    
}
