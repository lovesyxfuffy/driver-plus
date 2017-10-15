package com.driverPlus.dao.mapper.manage;

import com.driverPlus.dao.dto.manage.ServiceRecordDto;
import com.driverPlus.dao.po.manage.SchoolService;
import com.driverPlus.dao.po.manage.SchoolServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolServiceMapper {
    int countByExample(SchoolServiceExample example);

    int deleteByExample(SchoolServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchoolService record);

    int insertSelective(SchoolService record);

    List<SchoolService> selectByExample(SchoolServiceExample example);

    SchoolService selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchoolService record, @Param("example") SchoolServiceExample example);

    int updateByExample(@Param("record") SchoolService record, @Param("example") SchoolServiceExample example);

    int updateByPrimaryKeySelective(SchoolService record);

    int updateByPrimaryKey(SchoolService record);

    List<ServiceRecordDto> selectServiceRecordList();
}