package com.driverPlus.service.manage;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;




 /*
  Created by wangfeng on 17/10/7.
  */

public interface MessageService {
    void noticeToStudentMessage(String telephone,String content)throws Exception;
    boolean noticeMessage(String telephone,String content,String smsTemplateCode)throws Exception;

}
