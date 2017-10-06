package com.driverPlus.dao.mapper.manage;

import com.driverPlus.dao.po.manage.Field;
import com.driverPlus.dao.po.manage.FieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FieldMapper {
    int countByExample(FieldExample example);

    int deleteByExample(FieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Field record);

    int insertSelective(Field record);

    List<Field> selectByExample(FieldExample example);

    Field selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Field record, @Param("example") FieldExample example);

    int updateByExample(@Param("record") Field record, @Param("example") FieldExample example);

    int updateByPrimaryKeySelective(Field record);

    int updateByPrimaryKey(Field record);
}