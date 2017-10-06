package com.driverPlus.dao.mapper.manage;

import com.driverPlus.dao.po.manage.Service;
import com.driverPlus.dao.po.manage.ServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceMapper {
    int countByExample(ServiceExample example);

    int deleteByExample(ServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    List<Service> selectByExample(ServiceExample example);

    Service selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Service record, @Param("example") ServiceExample example);

    int updateByExample(@Param("record") Service record, @Param("example") ServiceExample example);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);
}