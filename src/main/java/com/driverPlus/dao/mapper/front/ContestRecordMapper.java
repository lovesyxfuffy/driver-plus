package com.driverPlus.dao.mapper.front;

import com.driverPlus.dao.po.front.ContestRecord;
import com.driverPlus.dao.po.front.ContestRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContestRecordMapper {
    int countByExample(ContestRecordExample example);

    int deleteByExample(ContestRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContestRecord record);

    int insertSelective(ContestRecord record);

    List<ContestRecord> selectByExample(ContestRecordExample example);

    ContestRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContestRecord record, @Param("example") ContestRecordExample example);

    int updateByExample(@Param("record") ContestRecord record, @Param("example") ContestRecordExample example);

    int updateByPrimaryKeySelective(ContestRecord record);

    int updateByPrimaryKey(ContestRecord record);
}