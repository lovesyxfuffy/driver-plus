package com.driverPlus.service.manage;

import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.dao.po.manage.ClassWithBLOBs;
import com.driverPlus.dao.po.manage.Field;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface ClassService {

    List<Class> getClassList();
    Map<Integer,Class> getAllClassMap();
    PageInfoResult<Class> getClassListWithPage(Integer pageNo,Integer pageSize);
    void addClass(ClassWithBLOBs classPo);
}
