package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.dto.manage.ServiceRecordDto;
import com.driverPlus.dao.mapper.manage.SchoolServiceMapper;
import com.driverPlus.dao.mapper.manage.ServiceMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.manage.ServiceExample;
import com.driverPlus.enums.ServiceTypeEnum;
import com.driverPlus.service.manage.ServiceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceMapper serviceMapper;
    @Autowired
    SchoolServiceMapper schoolServiceMapper;

    @Override
    public void updateServiceById(com.driverPlus.dao.po.manage.Service service){

        service.setUpdateTime(new Date());

        serviceMapper.updateByPrimaryKey(service);
    }
    @Override
    public PageInfoResult<ServiceRecordDto> getServceiRecordList(Integer pageNo,Integer pageSize){

        List<ServiceRecordDto> serviceRecordDtoList2=new ArrayList<>();
        PageHelper.startPage(pageNo,pageSize);
        List<ServiceRecordDto>  serviceRecordDtoList=schoolServiceMapper.selectServiceRecordList();

        if(CollectionUtils.isEmpty(serviceRecordDtoList)){
            return PageInfoResult.buildPage();
        }
        for(ServiceRecordDto serviceRecordDto:serviceRecordDtoList){
            ServiceRecordDto serviceRecordDto2=new ServiceRecordDto();
            BeanUtils.copyProperties(serviceRecordDto,serviceRecordDto2);
            serviceRecordDto2.setTypeStr(ServiceTypeEnum.getByCode(serviceRecordDto.getType())==null?"":ServiceTypeEnum.getByCode(serviceRecordDto.getType()).getName());
            serviceRecordDtoList2.add(serviceRecordDto2);
        }
        return PageInfoResult.buildPageFromList(serviceRecordDtoList,serviceRecordDtoList2);
    }
    @Override
    public List<ServiceRecordDto> getServceiList(){

        List<ServiceRecordDto> serviceRecordDtos=new ArrayList<>();
        ServiceExample example=new ServiceExample();
        List<com.driverPlus.dao.po.manage.Service> list=serviceMapper.selectByExample(example);
        for(com.driverPlus.dao.po.manage.Service service:list){
            ServiceRecordDto dto=new ServiceRecordDto();
            BeanUtils.copyProperties(service,dto);
            dto.setTypeStr(ServiceTypeEnum.getByCode(service.getType())==null?"":ServiceTypeEnum.getByCode(service.getType()).getName());
            serviceRecordDtos.add(dto);
        }

        return serviceRecordDtos;
    }

}
