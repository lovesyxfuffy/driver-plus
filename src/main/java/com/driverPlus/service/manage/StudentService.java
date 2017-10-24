package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.*;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.Student;
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

    List<Student> getStudentListById(List<Integer> idList);

    Map<Integer,Student> getStudentMap();

    PageInfoResult<StudyResultDto> searchStudyResultList(QueryStudyResultParam queryStudyResultParam);

    void createStudent(Student student);

    StudentDto getStudentInforById(int id);

}
