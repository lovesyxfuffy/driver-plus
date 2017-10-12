package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.dto.manage.OrderDto;
import com.driverPlus.dao.mapper.manage.OrderMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.dto.manage.QueryOrderParam;
import com.driverPlus.dao.po.manage.*;
import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.enums.OrderStatusEnum;
import com.driverPlus.enums.PayStatusEnum;
import com.driverPlus.service.manage.AgentService;
import com.driverPlus.service.manage.ClassService;
import com.driverPlus.service.manage.FieldService;
import com.driverPlus.service.manage.OrderService;
import com.driverPlus.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AgentService agentService;
    @Autowired
    ClassService classService;
    @Autowired
    FieldService fieldService;
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

    @Override
    public PageInfoResult<Order> serachOrderList(QueryOrderParam queryOrderParam){

        Map<Integer,Agent> agentMap=agentService.getAllAgentMapById();
        Map<Integer,Class>classMap=classService.getAllClassMap();
        Map<Integer,Field>fieldMap=fieldService.getAllFieldMap();
        List<OrderDto> orderDtoList=new ArrayList<>();
        PageHelper.startPage(queryOrderParam.getPageNo(),queryOrderParam.getPageSize());
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();

        if(!StringUtils.isEmpty(queryOrderParam.getClassId())){
            criteria.andClassIdEqualTo(queryOrderParam.getClassId());
        }
        if(!StringUtils.isEmpty(queryOrderParam.getFieldId())){
            criteria.andFieldIdEqualTo(queryOrderParam.getFieldId());
        }
        if(!StringUtils.isEmpty(queryOrderParam.getStudentName())){
            criteria.andStudentNameLike(queryOrderParam.getStudentName());
        }
        if(!StringUtils.isEmpty(queryOrderParam.getStudentIdcard())){
            criteria.andStudentIdcardEqualTo(queryOrderParam.getStudentIdcard());
        }
        if(!StringUtils.isEmpty(queryOrderParam.getTelephone())){
            criteria.andStudentTelephoneEqualTo(queryOrderParam.getTelephone());
        }
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        List<Order> orderList=orderMapper.selectByExample(example);


        for(Order order:orderList){
            OrderDto dto=new OrderDto();
            BeanUtils.copyProperties(order,dto);
            dto.setPayStatusStr(PayStatusEnum.getByCode(order.getPayStatus()).getName());
            dto.setStatusStr(OrderStatusEnum.getByCode(order.getStatus()).getName());
            dto.setRefereeName(agentMap.get(order.getRefereeId()).getRealName());
            dto.setClassName(classMap.get(order.getClassId()).getName());
            dto.setFieldName(fieldMap.get(order.getFieldId()).getName());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dto.setAddTime(sdf.format(order.getAddTime()));


            orderDtoList.add(dto);
        }
        return PageInfoResult.buildPageFromList(orderList,orderDtoList);
    }

    @Override
    public List<OrderDto>  serachOrderListNotPage(QueryOrderParam queryOrderParam){

        Map<Integer,Agent> agentMap=agentService.getAllAgentMapById();
        Map<Integer,Class>classMap=classService.getAllClassMap();
        Map<Integer,Field>fieldMap=fieldService.getAllFieldMap();
        List<OrderDto> orderDtoList=new ArrayList<>();
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();

        if(!StringUtils.isEmpty(queryOrderParam.getClassId())){
            criteria.andClassIdEqualTo(queryOrderParam.getClassId());
        }
        if(!StringUtils.isEmpty(queryOrderParam.getFieldId())){
            criteria.andFieldIdEqualTo(queryOrderParam.getFieldId());
        }
        if(!StringUtils.isEmpty(queryOrderParam.getStudentName())){
            criteria.andStudentNameEqualTo(queryOrderParam.getStudentName());
        }
        if(!StringUtils.isEmpty(queryOrderParam.getStudentIdcard())){
            criteria.andStudentIdcardEqualTo(queryOrderParam.getStudentIdcard());
        }
        if(!StringUtils.isEmpty(queryOrderParam.getTelephone())){
            criteria.andStudentTelephoneEqualTo(queryOrderParam.getTelephone());
        }
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        List<Order> orderList=orderMapper.selectByExample(example);


        for(Order order:orderList){
            OrderDto dto=new OrderDto();
            BeanUtils.copyProperties(order,dto);
            dto.setPayStatusStr(PayStatusEnum.getByCode(order.getPayStatus()).getName());
            dto.setStatusStr(OrderStatusEnum.getByCode(order.getStatus()).getName());
            dto.setRefereeName(agentMap.get(order.getRefereeId()).getRealName());
            dto.setClassName(classMap.get(order.getClassId())==null?"":classMap.get(order.getClassId()).getName());
            dto.setFieldName(fieldMap.get(order.getFieldId())==null?"":fieldMap.get(order.getFieldId()).getName());
            SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            dto.setAddTime(sdf.format(order.getAddTime()));


            orderDtoList.add(dto);
        }
        return orderDtoList;
    }


}
