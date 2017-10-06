package com.driverPlus.dao.mapper.front;

import com.driverPlus.dao.po.front.GroupRelation;
import com.driverPlus.dao.po.front.GroupRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupRelationMapper {
    int countByExample(GroupRelationExample example);

    int deleteByExample(GroupRelationExample example);

    int insert(GroupRelation record);

    int insertSelective(GroupRelation record);

    List<GroupRelation> selectByExample(GroupRelationExample example);

    int updateByExampleSelective(@Param("record") GroupRelation record, @Param("example") GroupRelationExample example);

    int updateByExample(@Param("record") GroupRelation record, @Param("example") GroupRelationExample example);
}