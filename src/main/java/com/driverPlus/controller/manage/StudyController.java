package com.driverPlus.controller.manage;

import com.driverPlus.service.manage.ConfigService;
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
@RequestMapping("/manage/study")
@Slf4j
public class StudyController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/setContestStatus",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> setContestStatus(@RequestParam List<Integer> idList,@RequestParam Integer status){


        studentService.updateStudentTestStatusById(idList,status);

        return WebUtil.success("操作成功");
    }


    
}
