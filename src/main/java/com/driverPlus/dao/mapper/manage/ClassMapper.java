package com.driverPlus.dao.mapper.manage;

import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.dao.po.manage.ClassExample;
import com.driverPlus.dao.po.manage.ClassWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassMapper {
    int countByExample(ClassExample example);

    int deleteByExample(ClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassWithBLOBs record);

    int insertSelective(ClassWithBLOBs record);

    List<ClassWithBLOBs> selectByExampleWithBLOBs(ClassExample example);

    List<Class> selectByExample(ClassExample example);

    ClassWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassWithBLOBs record, @Param("example") ClassExample example);

    int updateByExampleWithBLOBs(@Param("record") ClassWithBLOBs record, @Param("example") ClassExample example);

    int updateByExample(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByPrimaryKeySelective(ClassWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ClassWithBLOBs record);

    int updateByPrimaryKey(Class record);
}