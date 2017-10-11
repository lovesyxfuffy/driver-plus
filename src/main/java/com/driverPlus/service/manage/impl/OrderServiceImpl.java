package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.manage.OrderMapper;
import com.driverPlus.dao.po.manage.AccountExample;
import com.driverPlus.dao.po.manage.OrderExample;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.service.manage.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Integer getTodayTotalOrdersBySchoolIdAndStatus(Integer schoolId,Integer status){

       try {
           Date nowDate = new Date();
           SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
           SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
           SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


           String begindate = formatter1.format(nowDate);
           String enddate = formatter2.format(nowDate);


           OrderExample example = new OrderExample();
           if(status==-999) {
               example.createCriteria().andSchoolIdEqualTo(schoolId).andAddTimeGreaterThanOrEqualTo(formatter3.parse(begindate)).andAddTimeLessThan(formatter3.parse(enddate));
           }else{
               example.createCriteria().andSchoolIdEqualTo(schoolId).andStatusEqualTo(status).andAddTimeGreaterThanOrEqualTo(formatter3.parse(begindate)).andAddTimeLessThan(formatter3.parse(enddate));

           }
           return orderMapper.countByExample(example);
       }catch (Exception e){
           e.printStackTrace();
       }
       return 0;
    }


}
