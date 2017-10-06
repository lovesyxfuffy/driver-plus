package com.driverPlus.dao.mapper.manage;

import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.dao.po.manage.ConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {
    int countByExample(ConfigExample example);

    int deleteByExample(ConfigExample example);

    int deleteByPrimaryKey(String configKey);

    int insert(Config record);

    int insertSelective(Config record);

    List<Config> selectByExample(ConfigExample example);

    Config selectByPrimaryKey(String configKey);

    int updateByExampleSelective(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}