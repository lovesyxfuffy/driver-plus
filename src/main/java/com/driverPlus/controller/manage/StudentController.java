package com.driverPlus.controller.manage;

import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.dto.manage.EnumDto;
import com.driverPlus.dao.dto.manage.NoticeDto;
import com.driverPlus.dao.dto.manage.QueryStudentParam;
import com.driverPlus.dao.dto.manage.StudentDto;
import com.driverPlus.dao.po.front.Student;
import com.driverPlus.dao.po.front.User;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.enums.SchoolStatusEnum;
import com.driverPlus.service.manage.*;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @Autowired
    private SchoolsService schoolsService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getStatusEnum",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getStatusEnum(){

        List<EnumDto> list=configService.getGroupStatusList();

        return WebUtil.result(list);
    }
    @RequestMapping(value = "/addStatus",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addStatus(@RequestParam List<String> statusList){

        for(String status:statusList){
            configService.addGroupStatusList(status);
        }

        return WebUtil.success("操作成功");
    }

    @RequestMapping(value = "/editStatus",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> editStatus(@RequestBody EnumDto enumDto){

        configService.editGroupStatusList(enumDto.getId(),enumDto.getName());

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

    @RequestMapping(value = "/sendNotice",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> sendNotice(@RequestBody NoticeDto noticeDto) throws Exception{

        return WebUtil.success(noticeService.sendStudentNotice(noticeDto.getIdList(),noticeDto.getContent(),noticeDto.getName()));
    }

    //查看消息发送历史
    @RequestMapping(value = "/getNoticeList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getNoticeList(@RequestBody Map<String, Integer> requestParam){

        return WebUtil.result(noticeService.getNoticeListWithPage(requestParam.get("pageNo"),requestParam.get("pageSize")));
    }


    @RequestMapping(value = "/getLastSms",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getLastSms(){

        School school=schoolsService.getSchoolById(UserUtil.getSchoolId());
        Map<String,Integer> map=new HashMap<>();
        map.put("smsCount",school.getSmsCount());
        map.put("smsUsed",school.getSmsCount());

        return WebUtil.result(map);
    }

    //todo 驾校充值

    //todo 查看学员学习情况列表

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> create(@RequestBody StudentDto studentDto){

        Student student=new Student();
        BeanUtils.copyProperties(studentDto, student);
        List<User> userList=userService.getUserListByPhone(studentDto.getTelephone());
        if(!CollectionUtils.isEmpty(userList)){
            student.setUserId(userList.get(0).getId());
        }
        student.setSchoolId(UserUtil.getSchoolId());
        student.setContestCount(0);
        student.setAccuracy(100);
        student.setTestCount(0);
        student.setProcess(100);
        student.setTestStatus(1);
        studentService.createStudent(student);
        Map<String,Integer> map=new HashMap<>();
        map.put("id",student.getId());

        return WebUtil.result(map);
    }
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getInfo(@RequestBody Map<String, Integer> requestParam){

        Integer studentId=requestParam.get("studentId");

        return WebUtil.result(studentService.getStudentInforById(studentId));
    }







}
