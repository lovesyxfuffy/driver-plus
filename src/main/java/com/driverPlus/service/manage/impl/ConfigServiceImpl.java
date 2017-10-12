package com.driverPlus.service.manage.impl;


import com.driverPlus.dao.dto.manage.EnumDto;
import com.driverPlus.dao.mapper.manage.ConfigMapper;
import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.dao.po.manage.ConfigExample;
import com.driverPlus.service.manage.ConfigService;
import com.driverPlus.utils.UserUtil;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigMapper configMapper;

    @Override
    public List<EnumDto> getEnumDtoList(String type){
        ConfigExample example = new ConfigExample();
        ConfigExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andTypeEqualTo(type);

        List<Config> configList=configMapper.selectByExample(example);
        List<EnumDto> list=new ArrayList();
        for(Config config: configList){
            EnumDto dto=new EnumDto();
            dto.setName(config.getConfigName());
            dto.setId(Integer.parseInt(config.getConfigValue()));
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<Config> getConfigList(String type){
        ConfigExample example = new ConfigExample();
        ConfigExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andTypeEqualTo(type);

        List<Config> configList=configMapper.selectByExample(example);

        return configList;
    }

    /*@Override
    public void addGroupStatus(String type,String name){
        ConfigExample example = new ConfigExample();
        ConfigExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andTypeEqualTo(type);

        List<Config> configList=configMapper.selectByExample(example);
        List<EnumDto> list=new ArrayList();
        for(Config config: configList){
            EnumDto dto=new EnumDto();
            dto.setName(config.getConfigName());
            dto.setId(Integer.parseInt(config.getConfigValue()));
            list.add(dto);
        }
        return list;
    }*/

    @Override
    public void setConfig(String configKey,String configName,String configValue,String type){

        Config config=new Config();
        config.setConfigKey(configKey);
        config.setConfigName(configName);
        config.setConfigValue(configValue);
        config.setType(type);
        config.setAddTime(new Date());

        configMapper.insert(config);

    }


}
