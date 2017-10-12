package com.driverPlus.service.manage;

import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.Class;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface AgentService {

    List<Agent> getAgentList();
    Map<Integer,Agent> getAllAgentMapById();
    Map<String,Agent> getAllAgentMapByPhone();
}
