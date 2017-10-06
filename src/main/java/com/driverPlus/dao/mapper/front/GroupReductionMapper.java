package com.driverPlus.dao.mapper.front;

import com.driverPlus.dao.po.front.GroupReduction;
import com.driverPlus.dao.po.front.GroupReductionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupReductionMapper {
    int countByExample(GroupReductionExample example);

    int deleteByExample(GroupReductionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupReduction record);

    int insertSelective(GroupReduction record);

    List<GroupReduction> selectByExample(GroupReductionExample example);

    GroupReduction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupReduction record, @Param("example") GroupReductionExample example);

    int updateByExample(@Param("record") GroupReduction record, @Param("example") GroupReductionExample example);

    int updateByPrimaryKeySelective(GroupReduction record);

    int updateByPrimaryKey(GroupReduction record);
}