package com.driverPlus.controller.manage;

import com.driverPlus.dao.po.manage.School;
import com.driverPlus.enums.SchoolStatusEnum;
import com.driverPlus.service.manage.SchoolsService;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wangfeng on 2017/10/9.
 */
@RestController
@RequestMapping("/manage/Schools")
@Slf4j
public class SchoolController {

    @Autowired
    private SchoolsService schoolService;

    @RequestMapping(value = "/agreeAndRefuseSchools",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> test(@RequestBody Map<String, Integer> requestParam){


        Integer schoolId=requestParam.get("id");
        Integer type=requestParam.get("type");

        //查找注册的school信息
        School school=schoolService.getSchoolById(schoolId);

        //update操作
        if((SchoolStatusEnum.pay.getCode())==school.getStatus()){
            if(0==type){
                schoolService.updateSchoolStatusById(schoolId,SchoolStatusEnum.refuse.getCode());
            }else{
                schoolService.updateSchoolStatusById(schoolId,SchoolStatusEnum.agree.getCode());

            }

        }else{
            return WebUtil.error("该驾校不是处于待确认状态,无法操作");
        }



        return WebUtil.success("操作成功");
    }
}
