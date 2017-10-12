package com.driverPlus.controller.manage;

import com.driverPlus.dao.dto.manage.EnumDto;
import com.driverPlus.dao.dto.manage.QueryStudentParam;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.enums.SchoolStatusEnum;
import com.driverPlus.service.manage.ConfigService;
import com.driverPlus.service.manage.SchoolsService;
import com.driverPlus.service.manage.StudentService;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 2017/10/9.
 */
@RestController
@RequestMapping("/manage/student")
@Slf4j
public class StudentController {

    @Autowired
    private ConfigService configService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getStatusEnum",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getStatusEnum(){

        List<EnumDto> list=configService.getGroupStatusList();

        return WebUtil.result(list);
    }
    //todo 添加分组
    @RequestMapping(value = "/addStatus",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addStatus(@RequestParam List<String> statusList){

        for(String status:statusList){
            configService.addGroupStatusList(status);
        }

        return WebUtil.success("操作成功");
    }
    //todo 修改分组名称

    @RequestMapping(value = "/editStatus",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addStatus(@RequestParam Integer id,@RequestParam String status){

        configService.editGroupStatusList(id,status);

        return WebUtil.success("操作成功");
    }

    @RequestMapping(value = "/getStudentList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getStudentList(@RequestBody QueryStudentParam queryStudentParam){

        return WebUtil.result(studentService.serachStudentList(queryStudentParam));
    }
    @RequestMapping(value = "/changeStudentStatus",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> changeStudentStatus(@RequestBody Map<String, Integer> requestParam){

        studentService.updateStudentStatusById(requestParam.get("id"),requestParam.get("statusId"));

        return WebUtil.success("操作成功");
    }

    @RequestMapping(value = "/sendNotice",method = RequestMethod.POST)//// TODO: 17/10/12 发送通知
    public ResponseEntity<Map<String,Object>> sendNotice(@RequestBody Map<String, Integer> requestParam){


        return WebUtil.success("操作成功");
    }

    //todo 查看消息发送历史



    //todo 查看驾校短信余量

    //todo 驾校充值

    //todo 查看学员学习情况列表







}
