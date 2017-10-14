package com.driverPlus.controller.manage;

import com.driverPlus.dao.dto.manage.NoticeDto;
import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.dao.po.manage.Service;
import com.driverPlus.service.manage.ConfigService;
import com.driverPlus.service.manage.NoticeService;
import com.driverPlus.service.manage.ServiceService;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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




}
