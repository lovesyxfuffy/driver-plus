package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.OrderDto;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.dto.manage.QueryOrderParam;
import com.driverPlus.dao.po.manage.Order;

import java.util.List;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface OrderService {

    Integer getTodayTotalOrdersBySchoolIdAndStatus(Integer schoolId,Integer status);

    PageInfoResult<Order> serachOrderList(QueryOrderParam queryOrderParam);

    List<OrderDto> serachOrderListNotPage(QueryOrderParam queryOrderParam);

}
