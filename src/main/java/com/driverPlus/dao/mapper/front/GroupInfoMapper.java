package com.driverPlus.dao.mapper.front;

import com.driverPlus.dao.po.front.GroupInfo;
import com.driverPlus.dao.po.front.GroupInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupInfoMapper {
    int countByExample(GroupInfoExample example);

    int deleteByExample(GroupInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    List<GroupInfo> selectByExample(GroupInfoExample example);

    GroupInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByExample(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByPrimaryKeySelective(GroupInfo record);

    int updateByPrimaryKey(GroupInfo record);
}