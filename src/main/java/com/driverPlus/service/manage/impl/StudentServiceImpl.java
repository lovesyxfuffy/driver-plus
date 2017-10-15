package com.driverPlus.service.manage.impl;


import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.dto.manage.QueryStudentParam;
import com.driverPlus.dao.dto.manage.StudentDto;
import com.driverPlus.dao.dto.manage.StudentResultDto;
import com.driverPlus.dao.mapper.front.StudentMapper;
import com.driverPlus.dao.mapper.manage.OrderMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.Student;
import com.driverPlus.dao.po.front.StudentExample;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.OrderExample;
import com.driverPlus.enums.OrderStatusEnum;
import com.driverPlus.enums.StudentStatusEnum;
import com.driverPlus.service.manage.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public PageInfoResult<StudentDto> serachStudentList(QueryStudentParam queryStudentParam){

       List<StudentDto> studentDtoList=new ArrayList<>();
        PageHelper.startPage(queryStudentParam.getPageNo(),queryStudentParam.getPageSize());
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();

        if(!StringUtils.isEmpty(queryStudentParam.getName())){
            criteria.andNameLike("%"+queryStudentParam.getName()+"%");
        }
        if(!StringUtils.isEmpty(queryStudentParam.getIdcard())){
            criteria.andIdcardEqualTo(queryStudentParam.getIdcard());
        }
        if(!StringUtils.isEmpty(queryStudentParam.getTelephone())){
            criteria.andTelephoneEqualTo(queryStudentParam.getTelephone());
        }
        if(!StringUtils.isEmpty(queryStudentParam.getClassType())){
            criteria.andClassTypeEqualTo(queryStudentParam.getClassType());
        }

        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        List<Student> studentList=studentMapper.selectByExample(example);


        for(Student student:studentList){
            StudentDto dto=new StudentDto();
            BeanUtils.copyProperties(student,dto);
            dto.setStatusStr(StudentStatusEnum.getByCode(student.getStatus())==null?"":StudentStatusEnum.getByCode(student.getStatus()).getName());

            studentDtoList.add(dto);
        }
        return PageInfoResult.buildPageFromList(studentList,studentDtoList);
    }

    @Override
    public void updateStudentStatusById(Integer id,Integer status){
        Student student=new Student();
        student.setId(id);
        student.setStatus(status);
        studentMapper.updateByPrimaryKeySelective(student);
    }
    @Override
    public void updateStudentTestStatusById(List<Integer> idList,Integer status){
       //// TODO: 17/10/14
    }
    @Override
    public PageInfoResult<StudentResultDto> searchStudentByAgentIdWithPage(Integer agentId,Integer pageNo,Integer pageSize){

        PageHelper.startPage(pageNo,pageSize);
        List<StudentResultDto>studentList=studentMapper.selectStudentByAgentId(agentId,UserUtil.getSchoolId());
        if(CollectionUtils.isEmpty(studentList)){
            return PageInfoResult.buildPage();
        }
        return PageInfoResult.buildPageFromList(studentList);
    }

    @Override
    public List<StudentResultDto> searchStudentByAgentId(Integer agentId){

        return studentMapper.selectStudentByAgentId(agentId,UserUtil.getSchoolId());

    }
    @Override
    public List<Student> getStudentListById(List<Integer> idList){
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        criteria.andIdIn(idList);

        return studentMapper.selectByExample(example);
    }
    @Override
    public Map<Integer,Student> getStudentMap(){
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andSchoolIdEqualTo(UserUtil.getSchoolId());
        List<Student> list=studentMapper.selectByExample(example);
        Map<Integer,Student> map=new HashMap<>();
        for(Student student:list){
            map.put(student.getUserId(),student);
        }
        return map;
    }
}
