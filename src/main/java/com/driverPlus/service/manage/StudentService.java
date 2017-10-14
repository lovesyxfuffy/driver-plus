package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.QueryStudentParam;
import com.driverPlus.dao.dto.manage.StudentDto;
import com.driverPlus.dao.dto.manage.StudentResultDto;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.manage.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface StudentService {

    PageInfoResult<StudentDto> serachStudentList(QueryStudentParam queryStudentParam);

    void updateStudentStatusById(Integer id,Integer status);

    void updateStudentTestStatusById(List<Integer> idList,Integer status);

    PageInfoResult<StudentResultDto> searchStudentByAgentIdWithPage(Integer agentId, Integer pageNo, Integer pageSize);

    List<StudentResultDto> searchStudentByAgentId(Integer agentId);

}
