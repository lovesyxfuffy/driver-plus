package com.driverPlus.service.manage.impl;


import com.driverPlus.dao.dto.manage.QueryStudentParam;
import com.driverPlus.dao.dto.manage.StudentDto;
import com.driverPlus.dao.mapper.front.StudentMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.Student;
import com.driverPlus.dao.po.front.StudentExample;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.OrderExample;
import com.driverPlus.enums.StudentStatusEnum;
import com.driverPlus.service.manage.StudentService;
import com.driverPlus.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

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

    }
}
