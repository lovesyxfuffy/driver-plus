package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.manage.AgentMapper;
import com.driverPlus.dao.mapper.manage.ClassMapper;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.AgentExample;
import com.driverPlus.dao.po.manage.Class;
import com.driverPlus.dao.po.manage.ClassExample;
import com.driverPlus.service.manage.AgentService;
import com.driverPlus.service.manage.ClassService;
import com.driverPlus.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentMapper agentMapper;

    @Override
    public List<Agent> getAgentList() {
        AgentExample example = new AgentExample();
        AgentExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());

        return agentMapper.selectByExample(example);
    }
    @Override
    public Map<Integer,Agent> getAllAgentMapById(){
        Map<Integer,Agent> agentHashMap=new HashMap<>();
        List<Agent> agentList=getAgentList();
        for(Agent agent:agentList){
            agentHashMap.put(agent.getId(),agent);
        }
        return agentHashMap;
    }
    @Override
    public Map<String,Agent> getAllAgentMapByPhone(){
        Map<String,Agent> agentHashMap=new HashMap<>();
        List<Agent> agentList=getAgentList();
        for(Agent agent:agentList){
            agentHashMap.put(agent.getTelephone(),agent);
        }
        return agentHashMap;
    }
}
