package com.driverPlus.service.manage;

import com.driverPlus.dao.po.PageInfoResult;
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
    PageInfoResult<Field> getFiledList(Integer pageNo,Integer pageSize);
    Field getFieldDetail(Integer fieldId);
    void addField(Field field);
    void updateField(Field field);


}
