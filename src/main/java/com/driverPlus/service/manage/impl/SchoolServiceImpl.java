package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.dto.manage.QuerySchoolParam;
import com.driverPlus.dao.dto.manage.SchoolResultDto;
import com.driverPlus.dao.mapper.manage.SchoolMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.dao.po.manage.SchoolExample;
import com.driverPlus.enums.SchoolStatusEnum;
import com.driverPlus.service.manage.ConfigService;
import com.driverPlus.service.manage.SchoolsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    @Autowired
    ConfigService configService;

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
    @Override
    public PageInfoResult<SchoolResultDto> getSchoolResultList(QuerySchoolParam querySchoolParam){

        Map<Integer,Map<String,Config>> map=configService.getConfigMap();

        List<SchoolResultDto> schoolResultDtoList=new ArrayList<>();
        PageHelper.startPage(querySchoolParam.getPageNo(),querySchoolParam.getPageSize());
        SchoolExample example=new SchoolExample();
        SchoolExample.Criteria criteria=example.createCriteria();
        criteria.andCityEqualTo(querySchoolParam.getCity());
        List<School> schoolList=schoolMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(schoolList)){
            return PageInfoResult.buildPage();
        }
        for(School school:schoolList){
            SchoolResultDto dto=new SchoolResultDto();
            BeanUtils.copyProperties(school,dto);
            dto.setAdminEmail(map.get(school.getId())==null?"":(map.get(school.getId()).get("adminName")==null?"":map.get(school.getId()).get("adminName").getConfigName()));
            dto.setAdminJob(map.get(school.getId())==null?"":(map.get(school.getId()).get("adminEmail")==null?"":map.get(school.getId()).get("adminEmail").getConfigName()));
            dto.setAdminName(map.get(school.getId())==null?"":(map.get(school.getId()).get("adminJob")==null?"":map.get(school.getId()).get("adminJob").getConfigName()));
            schoolResultDtoList.add(dto);
        }
        return PageInfoResult.buildPageFromList(schoolList,schoolResultDtoList);
    }
}
