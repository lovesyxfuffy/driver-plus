package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.manage.SchoolMapper;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.service.manage.SchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class SchoolServiceImpl   implements SchoolsService {

    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public School getSchoolById(Integer id){

        return schoolMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateSchoolStatusById(Integer id,Integer status){

        School school=new School();
        school.setStatus(status);
        school.setId(id);
        schoolMapper.updateByPrimaryKey(school);
    }
    @Override
    public void updateSchoolById(School school){

        schoolMapper.updateByPrimaryKeySelective(school);
    }

}
