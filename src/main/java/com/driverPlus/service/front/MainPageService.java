package com.driverPlus.service.front;

import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.dao.po.manage.Field;

import java.util.List;

/**
 * Created by yujingyang on 2017/10/12.
 */
public interface MainPageService {
    List<Class> getClassList(Integer schoolId);

    List<Config> getConfigList(Integer schoolId);

    List<Field> getFieldList(Integer schoolId);
}
