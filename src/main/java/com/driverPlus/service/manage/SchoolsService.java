package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.QuerySchoolParam;
import com.driverPlus.dao.dto.manage.SchoolResultDto;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.manage.School;
import com.github.pagehelper.Page;

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

    PageInfoResult<SchoolResultDto> getSchoolResultList(QuerySchoolParam querySchoolParam);
}
