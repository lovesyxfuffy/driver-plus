package com.driverPlus.dao.mapper.manage;

import com.driverPlus.dao.dto.manage.OrderDto;
import com.driverPlus.dao.dto.manage.QueryOrderParam;
import com.driverPlus.dao.po.manage.Order;
import com.driverPlus.dao.po.manage.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderDto> searchOrderList(QueryOrderParam queryOrderParam);
}