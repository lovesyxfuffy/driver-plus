package com.driverPlus.service.manage;

import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.dao.po.manage.Field;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface FieldService {

    List<Field> getFieldList();
    Map<Integer,Field> getAllFieldMap();

}
