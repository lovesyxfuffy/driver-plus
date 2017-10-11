package com.driverPlus.controller.manage;

import com.driverPlus.dao.dto.manage.TodayOrderDto;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.enums.OrderStatusEnum;
import com.driverPlus.enums.PayWayEnum;
import com.driverPlus.enums.SchoolStatusEnum;
import com.driverPlus.service.manage.FieldService;
import com.driverPlus.service.manage.OrderService;
import com.driverPlus.service.manage.PayService;
import com.driverPlus.service.manage.SchoolsService;
import com.driverPlus.utils.UserUtil;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wangfeng on 2017/10/11.
 */
@RestController
@RequestMapping("/manage/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;
    @Autowired
    private FieldService fieldService;

    @RequestMapping(value = "/getStatistic",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getStatistic(@RequestBody Map<String, Integer> requestParam){


        Integer fieldId=requestParam.get("fieldId");
        Integer classId=requestParam.get("classId");

        Integer schoolId= UserUtil.getSchoolId();

        Integer orderCountToday=orderService.getTodayTotalOrdersBySchoolIdAndStatus(schoolId,-999);
        Integer orderConfirmed=orderService.getTodayTotalOrdersBySchoolIdAndStatus(schoolId, OrderStatusEnum.confirmed.getCode());
        Integer orderWaitForConfirmed=orderService.getTodayTotalOrdersBySchoolIdAndStatus(schoolId, OrderStatusEnum.paid.getCode());
        Integer orderCanceled=orderService.getTodayTotalOrdersBySchoolIdAndStatus(schoolId, OrderStatusEnum.canceled.getCode());
        Float payAmount=Float.parseFloat(payService.getTodayPayAmountBySchoolIdAndPayWay(schoolId, "-999")+"");
        Float onlinePayAmount=Float.parseFloat(payService.getTodayPayAmountBySchoolIdAndPayWay(schoolId,"other")+"");//其它支付方式
        Float offlinePayAmount=Float.parseFloat(payService.getTodayPayAmountBySchoolIdAndPayWay(schoolId,PayWayEnum.offline.getCode())+"");

        TodayOrderDto dto=new TodayOrderDto();
        dto.setOrderCountToday(orderCountToday);
        dto.setOrderConfirmed(orderConfirmed);
        dto.setOrderWaitForConfirmed(orderWaitForConfirmed);
        dto.setOrderCanceled(orderCanceled);
        dto.setPayAmount(payAmount);
        dto.setOnlinePayAmount(onlinePayAmount);
        dto.setOfflinePayAmount(offlinePayAmount);

        return WebUtil.result(dto);
    }

    @RequestMapping(value = "/getFieldEnum",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getFieldEnum(){

        return WebUtil.result(fieldService.getFieldList());
    }

}
