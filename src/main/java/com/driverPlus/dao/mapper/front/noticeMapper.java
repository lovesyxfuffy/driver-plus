package com.driverPlus.dao.mapper.front;

import com.driverPlus.dao.po.front.notice;
import com.driverPlus.dao.po.front.noticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface noticeMapper {
    int countByExample(noticeExample example);

    int deleteByExample(noticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(notice record);

    int insertSelective(notice record);

    List<notice> selectByExample(noticeExample example);

    notice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") notice record, @Param("example") noticeExample example);

    int updateByExample(@Param("record") notice record, @Param("example") noticeExample example);

    int updateByPrimaryKeySelective(notice record);

    int updateByPrimaryKey(notice record);
}