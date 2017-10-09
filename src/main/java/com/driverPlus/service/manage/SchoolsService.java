package com.driverPlus.service.manage;

import com.driverPlus.dao.po.manage.School;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface SchoolsService {

    School getSchoolById(Integer id);

    void updateSchoolStatusById(Integer id,Integer status);
}
