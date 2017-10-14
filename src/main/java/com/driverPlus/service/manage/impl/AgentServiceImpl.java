package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.mapper.manage.AgentMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.AgentExample;
import com.driverPlus.enums.AgentStatusEnum;
import com.driverPlus.service.manage.AgentService;
import com.driverPlus.Auth.UserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Override
    public void updateAgentById(Agent agent){

        agentMapper.updateByPrimaryKeySelective(agent);
    }
    @Override
    public Agent addAgent(Agent agentPo){
        Agent agent=new Agent();
        BeanUtils.copyProperties(agentPo,agent);
        agent.setAddTime(new Date());
        agent.setStatus(AgentStatusEnum.init.getCode());
        agentMapper.insert(agent);
        return agent;
    }

    @Override
    public PageInfoResult<Agent> searchAgentList(AgentDto agentDto){

        List<AgentDto> agentDtoList=new ArrayList<>();
        PageHelper.startPage(agentDto.getPageNo(),agentDto.getPageSize());
        AgentExample example=new AgentExample();
        AgentExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andStatusNotEqualTo(AgentStatusEnum.closed.getCode());

        List<Agent> agentList=agentMapper.selectByExample(example);
        for(Agent agent:agentList){
            AgentDto dto=new AgentDto();
            BeanUtils.copyProperties(agent,dto);
            agentDtoList.add(dto);
        }
        return PageInfoResult.buildPageFromList(agentList,agentDtoList);

    }

}
