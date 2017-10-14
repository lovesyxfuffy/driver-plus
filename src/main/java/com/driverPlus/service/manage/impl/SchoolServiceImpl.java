package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.manage.SchoolMapper;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.dao.po.manage.SchoolExample;
import com.driverPlus.enums.SchoolStatusEnum;
import com.driverPlus.service.manage.SchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
    public List<School> getSchoolList(){

        SchoolExample example=new SchoolExample();

        return schoolMapper.selectByExample(example);

    }
    @Override
    public Map<Integer,School> getSchoolMap(){

        List<School> schoolList=getSchoolList();
        Map<Integer,School> schoolMap=new HashMap<>();
        for(School school:schoolList){
            schoolMap.put(school.getId(),school);
        }


        return schoolMap;
    }
}
