package com.driverPlus.controller.front;

import com.driverPlus.Auth.WechatUserUtil;
import com.driverPlus.service.front.MainPageService;
import com.driverPlus.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by yujingyang on 2017/10/12.
 */
@Controller
@RequestMapping("/front/mainPage/")
public class MainPageController {

    @Autowired
    MainPageService mainPageService;

    @RequestMapping(value = "/getClassList", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getClassList() {
        Integer schoolId = WechatUserUtil.getSchoolId();
        if (schoolId == null)
            return WebUtil.error("系统错误");
        return WebUtil.result(mainPageService.getClassList(schoolId));
    }

    @RequestMapping(value = "/getMainPageConfig", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getMainPageConfig() {
        Integer schoolId = WechatUserUtil.getSchoolId();
        if (schoolId == null)
            return WebUtil.error("系统错误");
        return WebUtil.result(mainPageService.getConfigList(schoolId));
    }

    @RequestMapping(value = "/getFieldList", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getFieldList() {
        Integer schoolId = WechatUserUtil.getSchoolId();
        if (schoolId == null)
            return WebUtil.error("系统错误");
        return WebUtil.result(mainPageService.getFieldList(schoolId));
    }
}
