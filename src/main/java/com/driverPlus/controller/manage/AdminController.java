package com.driverPlus.controller.manage;

import com.driverPlus.dao.dto.manage.NoticeDto;
import com.driverPlus.dao.dto.manage.QueryOrderParam;
import com.driverPlus.dao.dto.manage.QuerySchoolParam;
import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.dao.po.manage.Service;
import com.driverPlus.service.manage.*;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 2017/10/9.
 */
@RestController
@RequestMapping("/manage/admin")
@Slf4j
public class AdminController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private  SchoolsService schoolsService;

    @RequestMapping(value = "/updateService",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateService(@RequestBody Service service){

        serviceService.updateServiceById(service);
        return WebUtil.success("操作成功");
    }
    @RequestMapping(value = "/getServiceRecord",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getServiceRecord(@RequestBody Map<String,Integer> requestParam){

        return WebUtil.result(serviceService.getServceiRecordList(requestParam.get("pageNo"),requestParam.get("pageSize")));
    }
    @RequestMapping(value = "/getServiceList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getServiceList(){

        return WebUtil.result(serviceService.getServceiList());
    }

    @RequestMapping(value = "/sendNotice",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> sendNotice(@RequestBody NoticeDto noticeDto){


        noticeService.sendSchoolNotice(noticeDto);
        return WebUtil.success("通知成功");
    }

    @RequestMapping(value = "/getNoticeList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getNoticeList(@RequestBody Map<String,Integer> requestParam){


        return WebUtil.result(noticeService.getSNoticeListWithPage(requestParam.get("pageNo"),requestParam.get("pageSize")));
    }

    @RequestMapping(value = "/getSchoolStudentCount",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getSchoolStudentCount(@RequestBody Map<String,Integer> requestParam){

        Integer todayCount=orderService.getStudentCountByToday(requestParam.get("schoolId"));
        Integer monthCount=orderService.getStudentCountByMonth(requestParam.get("schoolId"));
        Integer totalCount=orderService.getStudentCountTotal(requestParam.get("schoolId"));
        Map<String,Integer> map=new HashMap<>();
        map.put("day",todayCount);
        map.put("month",monthCount);
        map.put("amount",todayCount);
        return WebUtil.result(map);
    }

    @RequestMapping(value = "/getSchoolList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getSchoolList(@RequestBody QuerySchoolParam querySchoolParam){


        return WebUtil.result(schoolsService.getSchoolResultList(querySchoolParam));
    }

    @RequestMapping(value = "/order/searchOrderList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> searchOrderList(@RequestBody QueryOrderParam queryOrderParam){


        return WebUtil.result(orderService.searchManageOrderListWithPage(queryOrderParam));
    }




}
