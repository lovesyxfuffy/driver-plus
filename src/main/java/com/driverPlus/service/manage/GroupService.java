package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.GroupInfo;
import com.driverPlus.dao.po.front.GroupReduction;
import com.driverPlus.dao.po.front.GroupRelation;
import com.driverPlus.dao.po.manage.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface GroupService {

    List<GroupInfo> getGroupInfoList();
    List<GroupReduction>getGroupReductionList();
    List<GroupRelation>getGroupRelationList();
    Map<Integer,List<GroupReduction>> getGroupReductionMap();
    Map<Integer,Integer> getGroupCountByStudent(List<GroupRelation> groupRelationList);
    Map<Integer,Integer> getGroupCountByOwer(List<GroupRelation> groupRelationList);

}
