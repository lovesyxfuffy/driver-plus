package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.dto.manage.FieldDto;
import com.driverPlus.dao.mapper.manage.FieldMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.manage.Field;
import com.driverPlus.dao.po.manage.FieldExample;
import com.driverPlus.service.manage.FieldService;
import com.driverPlus.Auth.UserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Override
    public PageInfoResult<Field> getFiledList(Integer pageNo, Integer pageSize){
        List<FieldDto> fieldDtoList=new ArrayList<>();
        PageHelper.startPage(pageNo,pageSize);
        List<Field> list=getFieldList();
        if(CollectionUtils.isEmpty(list)){
            return PageInfoResult.buildPage();
        }
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
        for(Field field:list){
            FieldDto fieldDto=new FieldDto();
            fieldDto.setId(field.getId());
            fieldDto.setName(field.getName());
            fieldDto.setAddTime(field.getAddTime()!=null?sdf.format(field.getAddTime()):"");
            fieldDtoList.add(fieldDto);
        }
        return PageInfoResult.buildPageFromList(list,fieldDtoList);
    }
    @Override
    public Field getFieldDetail(Integer fieldId){
        return fieldMapper.selectByPrimaryKey(fieldId);
    }
    @Override
    public void addField(Field field){
         fieldMapper.insert(field);
    }
    @Override
    public void updateField(Field field){
        fieldMapper.insertSelective(field);
    }

}
