package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.dto.manage.OrderDto;
import com.driverPlus.dao.mapper.manage.OrderMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.dto.manage.QueryOrderParam;
import com.driverPlus.dao.po.front.GroupRelation;
import com.driverPlus.dao.po.manage.*;
import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.enums.OrderStatusEnum;
import com.driverPlus.enums.PayStatusEnum;
import com.driverPlus.service.manage.*;
import com.driverPlus.Auth.UserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
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
    @Autowired
    GroupService groupService;
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
        if(CollectionUtils.isEmpty(orderList)){
            return PageInfoResult.buildPage();
        }
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
            dto.setTelephone(order.getStudentTelephone());



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
            dto.setTelephone(order.getStudentTelephone());


            orderDtoList.add(dto);
        }
        return orderDtoList;
    }

    @Override
    public List<Order> getOrderListById(List<Integer> idList){
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(idList);
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        return orderMapper.selectByExample(example);
    }
    @Override
    public List<Order> getWaitConfirmOrderListById(List<Integer> idList){
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(idList);
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andStatusEqualTo(OrderStatusEnum.paid.getCode());
        return orderMapper.selectByExample(example);
    }
    @Override
    public List<Order> getConfirmOrderListById(List<Integer> idList){
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(idList);
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andStatusEqualTo(OrderStatusEnum.confirmed.getCode());
        return orderMapper.selectByExample(example);
    }
    @Override
    public void cancelOrderListById(List<Integer> idList){
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(idList);
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andStatusEqualTo(OrderStatusEnum.confirmed.getCode());
        Order order=new Order();
        order.setStatus(OrderStatusEnum.canceled.getCode());
        orderMapper.updateByExampleSelective(order,example);
    }
    @Override
    public void confirmOrderListById(List<Integer> idList){
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andIdIn(idList);
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andStatusEqualTo(OrderStatusEnum.paid.getCode());

        Order order=new Order();
        order.setStatus(OrderStatusEnum.confirmed.getCode());
        orderMapper.updateByExampleSelective(order,example);
    }
    @Override
    public PageInfoResult<OrderDto> serachOrderListByOwnerId(QueryOrderParam queryOrderParam){

        Map<Integer,Agent> agentMap=agentService.getAllAgentMapById();
        Map<Integer,Class>classMap=classService.getAllClassMap();
        Map<Integer,Field>fieldMap=fieldService.getAllFieldMap();
        List<GroupRelation> groupRelationList=groupService.getGroupRelationListByOwnerId(queryOrderParam.getOwnerId());
        List<Integer> userIdList=new ArrayList<>();
        for(GroupRelation groupRelation:groupRelationList){
            userIdList.add(groupRelation.getUserId());
        }
        List<OrderDto> orderDtoList=new ArrayList<>();
        PageHelper.startPage(queryOrderParam.getPageNo(),queryOrderParam.getPageSize());
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdIn(userIdList);
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        List<Order> orderList=orderMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(orderList)){
            return PageInfoResult.buildPage();
        }

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
            dto.setTelephone(order.getStudentTelephone());

        }
        return PageInfoResult.buildPageFromList(orderList,orderDtoList);

    }
    @Override
    public Integer getStudentCountByToday(Integer schoolId){

        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId);
        criteria.andStatusEqualTo(OrderStatusEnum.confirmed.getCode());
        Date nowDate = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String begindate = formatter1.format(nowDate);
        String enddate = formatter2.format(nowDate);

        try {
            criteria.andAddTimeBetween(formatter3.parse(begindate),formatter3.parse(enddate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderMapper.countByExample(example);

    }

    @Override
    public Integer getStudentCountByMonth(Integer schoolId){
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId);
        criteria.andStatusEqualTo(OrderStatusEnum.confirmed.getCode());
        Date nowDate = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String begindate = formatter1.format(nowDate);

        try {
            criteria.andAddTimeBetween(formatter3.parse(begindate),nowDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orderMapper.countByExample(example);
    }

    @Override
    public Integer getStudentCountTotal(Integer schoolId){
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId);
        criteria.andStatusEqualTo(OrderStatusEnum.confirmed.getCode());

        return orderMapper.countByExample(example);
    }
    @Override
    public  PageInfoResult<OrderDto> searchManageOrderListWithPage(QueryOrderParam queryOrderParam){
        Map<Integer,Agent> agentMap=agentService.getAllAgentMapById();
        Map<Integer,Class>classMap=classService.getAllClassMap();
        Map<Integer,Field>fieldMap=fieldService.getAllFieldMap();

        PageHelper.startPage(queryOrderParam.getPageNo(),queryOrderParam.getPageSize());
        List<OrderDto> orderDtoList=orderMapper.searchOrderList(queryOrderParam);
        if(CollectionUtils.isEmpty(orderDtoList)){
            return PageInfoResult.buildPage();
        }

        for(OrderDto orderDto:orderDtoList){

            orderDto.setPayStatusStr(PayStatusEnum.getByCode(orderDto.getPayStatus()).getName());
            orderDto.setStatusStr(OrderStatusEnum.getByCode(orderDto.getStatus()).getName());
            orderDto.setClassName(classMap.get(orderDto.getClassId())==null?"":classMap.get(orderDto.getClassId()).getName());
            orderDto.setFieldName(fieldMap.get(orderDto.getFieldId())==null?"":fieldMap.get(orderDto.getFieldId()).getName());

        }
        return PageInfoResult.buildPageFromList(orderDtoList);
    }

}
