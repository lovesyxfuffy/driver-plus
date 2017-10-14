package com.driverPlus.service.manage;

import com.driverPlus.dao.po.manage.School;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface SchoolsService {

    School getSchoolById(Integer id);

    void updateSchoolStatusById(Integer id,Integer status);

    void updateSchoolById(School school);

    List<School> getSchoolList();

    Map<Integer,School> getSchoolMap();
}
