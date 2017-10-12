package com.driverPlus.controller.manage;

import com.driverPlus.dao.dto.manage.ClassTypeDto;
import com.driverPlus.dao.dto.manage.OrderDto;
import com.driverPlus.dao.dto.manage.TodayOrderDto;
import com.driverPlus.dao.dto.manage.QueryOrderParam;
import com.driverPlus.dao.po.manage.Order;
import com.driverPlus.enums.ClassTypeEnum;
import com.driverPlus.enums.OrderStatusEnum;
import com.driverPlus.enums.PayWayEnum;
import com.driverPlus.service.manage.ClassService;
import com.driverPlus.service.manage.FieldService;
import com.driverPlus.service.manage.OrderService;
import com.driverPlus.service.manage.PayService;
import com.driverPlus.utils.ExcelUtil;
import com.driverPlus.utils.UserUtil;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    @Autowired
    private ClassService classService;

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
    @RequestMapping(value = "/searchOrderList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getStatistic(@RequestBody QueryOrderParam queryOrderParam){

        return WebUtil.result(orderService.serachOrderList(queryOrderParam));
    }

    @RequestMapping(value = "/getFieldEnum",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getFieldEnum(){

        return WebUtil.result(fieldService.getFieldList());
    }
    @RequestMapping(value = "/getClassEnum",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getClassEnum(){

        return WebUtil.result(classService.getClassList());
    }
    @RequestMapping(value = "/getTypeEnum",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getTypeEnum(){
        List<ClassTypeDto> list=new ArrayList<>();

        for(ClassTypeEnum item: ClassTypeEnum.values()){
            ClassTypeDto dto=new ClassTypeDto();
            dto.setName(item.getCode());
            list.add(dto);
        }
        return WebUtil.result(list);
    }

    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportExcel(@RequestParam Integer fieldId, @RequestParam Integer classId,
                                              @RequestParam String studentName, @RequestParam String studentIdcard, @RequestParam String telephone)
    throws Exception{


        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();

        fieldMap.put("studentName","学员姓名");
        fieldMap.put("studentIdcard","身份证");
        fieldMap.put("telephone","手机号");
        fieldMap.put("refereeName","代理人");
        fieldMap.put("className","班型名称");
        fieldMap.put("fieldName","场地");
        fieldMap.put("statusStr","支付状态");
        fieldMap.put("addTime","添加时间");


        QueryOrderParam queryOrderParam=new QueryOrderParam();
        queryOrderParam.setClassId(classId);
        queryOrderParam.setFieldId(fieldId);
        queryOrderParam.setStudentIdcard(studentIdcard);
        queryOrderParam.setStudentName(studentName);
        queryOrderParam.setTelephone(telephone);
        try{
            List<OrderDto> list=orderService.serachOrderListNotPage(queryOrderParam);
            XSSFWorkbook xSSFWorkbook=ExcelUtil.exportExcel(fieldMap,list);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            xSSFWorkbook.write(out);
            HttpHeaders headers = new HttpHeaders();
            String fileName = new String("订单.xlsx".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            ResponseEntity<byte[]> filebyte = new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
            out.close();
            return filebyte;
    } catch (InvocationTargetException e) {
        e.printStackTrace();

        return null;
    } catch (IllegalAccessException e) {
        e.printStackTrace();
        return null;

    } catch (IOException e) {
        e.printStackTrace();
        return null;

    }
    }
    @RequestMapping(value = "/confirm",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> confirm(@RequestParam List<Integer> idList){

        orderService.confirmOrderListById(idList);


        return WebUtil.success("操作成功");
    }
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> cancel(@RequestParam List<Integer> idList){

        orderService.cancelOrderListById(idList);

        return WebUtil.success("操作成功");
    }

}
