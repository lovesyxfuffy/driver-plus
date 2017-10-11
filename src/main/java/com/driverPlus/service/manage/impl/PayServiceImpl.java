package com.driverPlus.service.manage.impl;


import com.driverPlus.dao.mapper.manage.PayMapper;
import com.driverPlus.dao.po.manage.PayExample;
import com.driverPlus.enums.PayWayEnum;
import com.driverPlus.service.manage.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    PayMapper payMapper;

    @Override
    public Integer getTodayPayAmountBySchoolIdAndPayWay(Integer schoolId,String payWay){

       try {
           Date nowDate = new Date();
           SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
           SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
           SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


           String begindate = formatter1.format(nowDate);
           String enddate = formatter2.format(nowDate);


           PayExample example = new PayExample();
           if("-999".equals(payWay)) {
               example.createCriteria().andSchoolIdEqualTo(schoolId).andAddTimeGreaterThanOrEqualTo(formatter3.parse(begindate)).andAddTimeLessThan(formatter3.parse(enddate));
           }else if((PayWayEnum.offline.getCode()).equals(payWay)){
               example.createCriteria().andSchoolIdEqualTo(schoolId).andPayWayEqualTo(payWay).andAddTimeGreaterThanOrEqualTo(formatter3.parse(begindate)).andAddTimeLessThan(formatter3.parse(enddate));
           }else{
               example.createCriteria().andSchoolIdEqualTo(schoolId).andPayWayNotEqualTo(PayWayEnum.offline.getCode()).andAddTimeGreaterThanOrEqualTo(formatter3.parse(begindate)).andAddTimeLessThan(formatter3.parse(enddate));
           }
           return payMapper.sumPayByExample(example);


       }catch (Exception e){
           e.printStackTrace();
       }
       return 0;
    }


}
