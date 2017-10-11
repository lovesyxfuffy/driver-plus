package com.driverPlus.service.manage;

import com.driverPlus.dao.po.manage.School;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface OrderService {

    Integer getTodayTotalOrdersBySchoolIdAndStatus(Integer schoolId,Integer status);
}
