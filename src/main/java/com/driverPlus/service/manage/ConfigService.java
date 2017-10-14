package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.EnumDto;
import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.dao.po.manage.Field;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface ConfigService {

    List<EnumDto>getGroupStatusList();
    List<EnumDto> getEnumDtoList(String type);
    List<Config> getConfigList(String type);
    void setConfig(String configKey,String configName,String configValue,String type);
    void addGroupStatusList(String status);
    void editGroupStatusList(Integer id,String status);

}
