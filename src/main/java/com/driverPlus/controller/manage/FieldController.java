package com.driverPlus.controller.manage;

import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.po.manage.Field;
import com.driverPlus.service.manage.FieldService;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 2017/10/9.
 */
@RestController
@RequestMapping("/manage/field")
@Slf4j
public class FieldController {

    @Autowired
    private FieldService fieldService;


    @RequestMapping(value = "/getFieldList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getFieldList(@RequestBody Map<String, Integer> requestParam){

        Integer pageNo=requestParam.get("pageNo");
        Integer pageSize=requestParam.get("pageSize");

        return WebUtil.result(fieldService.getFiledList(pageNo,pageSize));
    }

    @RequestMapping(value = "/getField",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getField(@RequestBody Map<String, Integer> requestParam){

        Integer fieldId=requestParam.get("fieldId");

        return WebUtil.result(fieldService.getFieldDetail(fieldId));
    }
    @RequestMapping(value = "/createField",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> createField(@RequestBody Field field){

        field.setSchoolId(UserUtil.getSchoolId());
        field.setStatus(1);
        field.setAddTime(new Date());

        fieldService.addField(field);

        return WebUtil.success("操作成功");
    }
    @RequestMapping(value = "/updateField",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateField(@RequestBody Field field){


        field.setUpdateTime(new Date());

        fieldService.updateField(field);

        return WebUtil.success("操作成功");
    }



}
