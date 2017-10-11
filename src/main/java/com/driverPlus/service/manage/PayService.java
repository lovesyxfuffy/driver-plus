package com.driverPlus.service.manage;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface PayService {

    Integer getTodayPayAmountBySchoolIdAndPayWay(Integer schoolId,String payWay);
}
