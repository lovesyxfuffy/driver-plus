package com.driverPlus.service.manage.impl;

import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.dto.manage.EnumDto;
import com.driverPlus.dao.dto.manage.GroupInfoListDto;
import com.driverPlus.dao.mapper.front.GroupInfoMapper;
import com.driverPlus.dao.mapper.front.GroupReductionMapper;
import com.driverPlus.dao.mapper.front.GroupRelationMapper;
import com.driverPlus.dao.mapper.manage.AgentMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.*;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.AgentExample;
import com.driverPlus.enums.AgentStatusEnum;
import com.driverPlus.service.manage.AgentService;
import com.driverPlus.service.manage.GroupService;
import com.driverPlus.service.manage.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupInfoMapper groupInfoMapper;
    @Autowired
    GroupReductionMapper reductionMapper;
    @Autowired
    GroupRelationMapper groupRelationMapper;
    @Autowired
    StudentService studentService;

    @Override
    public List<GroupInfo> getGroupInfoList(){
        GroupInfoExample example=new GroupInfoExample();
        GroupInfoExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());

        return groupInfoMapper.selectByExample(example);

    }
    @Override
    public List<GroupReduction>getGroupReductionList(){
        GroupReductionExample example=new GroupReductionExample();
        GroupReductionExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());

        return reductionMapper.selectByExample(example);

    }
    @Override
    public List<GroupRelation>getGroupRelationList(){
        List<GroupInfo> groupInfoList=getGroupInfoList();
        List<Integer> idList=new ArrayList<>();
        for(GroupInfo groupInfo:groupInfoList){
            idList.add(groupInfo.getId());
        }
        GroupRelationExample example=new GroupRelationExample();
        GroupRelationExample.Criteria criteria=example.createCriteria();
        criteria.andGroupIdIn(idList);

        return groupRelationMapper.selectByExample(example);
    }
    @Override
    public List<GroupRelation>getGroupRelationListByOwnerId(Integer ownerId){

        GroupRelationExample example=new GroupRelationExample();
        GroupRelationExample.Criteria criteria=example.createCriteria();
        criteria.andOwnerIdEqualTo(ownerId);

        return groupRelationMapper.selectByExample(example);
    }
    @Override
    public Map<Integer,List<GroupReduction>> getGroupReductionMap(){
        List<GroupReduction> reductionList=getGroupReductionList();
        Map<Integer,List<GroupReduction>> reductionMap=new HashMap<>();

        for(GroupReduction groupReduction:reductionList){
            List<GroupReduction> list=reductionMap.get(groupReduction.getGroupId());
            if(!reductionMap.containsKey(groupReduction.getGroupId())){
                list=new ArrayList<>();
            }
            list.add(groupReduction);
            reductionMap.put(groupReduction.getGroupId(),list);
        }
        return reductionMap;
    }
    @Override
    public Map<Integer,Integer> getGroupCountByStudent(List<GroupRelation> groupRelationList){

        Map<Integer,Integer> countStudentMap=new HashMap<>();

        for(GroupRelation groupRelation:groupRelationList){
            Integer count=countStudentMap.get(groupRelation.getGroupId());
            if(!countStudentMap.containsKey(groupRelation.getGroupId())){
                count=0;

            }
            count+=1;
            countStudentMap.put(groupRelation.getGroupId(),count);

        }
        return countStudentMap;

    }
    @Override
    public Map<Integer,Integer> getGroupCountByOwer(List<GroupRelation> groupRelationList){

        Map<Integer,Integer> countOwerMap=new HashMap<>();

        Map<Integer,Integer> existMap=new HashMap<>();
        for(GroupRelation groupRelation:groupRelationList){
            if(!existMap.containsKey(groupRelation.getOwnerId())){
                Integer count = countOwerMap.get(groupRelation.getGroupId());
                if (!countOwerMap.containsKey(groupRelation.getGroupId())) {
                    count = 0;

                }
                count += 1;
                countOwerMap.put(groupRelation.getGroupId(), count);
                existMap.put(groupRelation.getOwnerId(),1);
            }

        }
        return countOwerMap;

    }
    @Override
    public void updateGroupInfo(GroupInfoListDto dto){
        GroupInfo groupInfo=new GroupInfo();
        BeanUtils.copyProperties(dto,groupInfo);

        groupInfoMapper.updateByPrimaryKeySelective(groupInfo);

        List<GroupReduction> reductionList=dto.getReductionList();
        for(GroupReduction groupReduction:reductionList){
            reductionMapper.updateByPrimaryKeySelective(groupReduction);
        }
    }
    @Override
    public List<EnumDto> getGroupOwnerEnum(){

        List<EnumDto> enumDtoList=new ArrayList<>();
        Map<Integer,Student> map=studentService.getStudentMap();
        List<GroupRelation> groupRelationList=getGroupRelationList();
        Map<Integer,Integer> existMap=new HashMap<>();
        for(GroupRelation groupRelation:groupRelationList) {
            if (!existMap.containsKey(groupRelation.getOwnerId())) {
                EnumDto dto=new EnumDto();
                dto.setId(groupRelation.getOwnerId());
                dto.setName(map.get(groupRelation.getOwnerId())==null?"":map.get(groupRelation.getOwnerId()).getName());
            }
        }
        return enumDtoList;
    }


}
