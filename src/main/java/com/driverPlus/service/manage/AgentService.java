package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.po.PageInfoResult;
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
    void updateAgentById(Agent agent);
    Agent addAgent(Agent agentPo);
    PageInfoResult<Agent> searchAgentList(AgentDto agentDto);
}
