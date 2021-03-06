package com.driverPlus.dao.mapper.manage;

import com.driverPlus.dao.po.manage.Pay;
import com.driverPlus.dao.po.manage.PayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMapper {
    int countByExample(PayExample example);

    int deleteByExample(PayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pay record);

    int insertSelective(Pay record);

    List<Pay> selectByExample(PayExample example);

    Pay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pay record, @Param("example") PayExample example);

    int updateByExample(@Param("record") Pay record, @Param("example") PayExample example);

    int updateByPrimaryKeySelective(Pay record);

    int updateByPrimaryKey(Pay record);

    int sumPayByExample(PayExample example);

}