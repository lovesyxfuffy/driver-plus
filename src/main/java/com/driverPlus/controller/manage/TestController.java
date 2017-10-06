package com.driverPlus.controller.manage;

import com.driverPlus.utils.WebUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by yujingyang on 2017/10/6.
 */
@Controller
public class TestController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> test(){
        return WebUtil.result("1");
    }
}
