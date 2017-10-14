package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.Notice;
import com.driverPlus.dao.po.manage.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface TNoticeService {

    List<Notice> getNoticeList();
}
