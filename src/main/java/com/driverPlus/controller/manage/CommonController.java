package com.driverPlus.controller.manage;

import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.service.manage.ConfigService;
import com.driverPlus.service.manage.NoticeService;
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
@RequestMapping("/manage/common")
@Slf4j
public class CommonController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/setConfig",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> agreeAndRefuseSchools(@RequestBody Map<String, String> requestParam){

        configService.setConfig(requestParam.get("configKey"),requestParam.get("configName"),requestParam.get("configValue"),requestParam.get("type"));

        return WebUtil.success("操作成功");
    }

    //todo 根据类型获取所有配置 没有ID
    @RequestMapping(value = "/getConfigByType",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getConfigByType(@RequestBody Map<String, String> requestParam){

        List<Config> configList=configService.getConfigList(requestParam.get("typeName"));

        return WebUtil.result(configList);
    }

    @RequestMapping(value = "/getNotice",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getNotice(){

        return WebUtil.result(noticeService.getSNoticeList());
    }



}
