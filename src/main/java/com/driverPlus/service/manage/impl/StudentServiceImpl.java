package com.driverPlus.service.manage.impl;


import com.driverPlus.Auth.UserUtil;
import com.driverPlus.contants.ConfigKeyConstants;
import com.driverPlus.dao.dto.manage.*;
import com.driverPlus.dao.mapper.front.StudentMapper;
import com.driverPlus.dao.mapper.manage.OrderMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.AnswerRecord;
import com.driverPlus.dao.po.front.ContestRecord;
import com.driverPlus.dao.po.front.Student;
import com.driverPlus.dao.po.front.StudentExample;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.Config;
import com.driverPlus.dao.po.manage.OrderExample;
import com.driverPlus.enums.OrderStatusEnum;
import com.driverPlus.enums.StudentStatusEnum;
import com.driverPlus.service.manage.AnswerRecordService;
import com.driverPlus.service.manage.ConfigService;
import com.driverPlus.service.manage.ContestRecordService;
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
    @Autowired
    ConfigService configService;
    @Autowired
    AnswerRecordService answerRecordService;
    @Autowired
    ContestRecordService contestRecordService;

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
    @Override
    public PageInfoResult<StudyResultDto> searchStudyResultList(QueryStudyResultParam queryStudyResultParam){

        Map<Integer,Map<String,Config>> map=configService.getConfigMap();
        Map<String,Config> configMap=map.get(UserUtil.getSchoolId());
        Float exam_setted_condition1_1=Float.parseFloat(configMap.get(ConfigKeyConstants.exam_setted_condition1_1)==null?"100":configMap.get(ConfigKeyConstants.exam_setted_condition1_1).getConfigValue());
        Float exam_setted_condition1_2=Float.parseFloat(configMap.get(ConfigKeyConstants.exam_setted_condition1_2)==null?"100":configMap.get(ConfigKeyConstants.exam_setted_condition1_2).getConfigValue());
        Integer exam_setted_condition2_1=Integer.parseInt(configMap.get(ConfigKeyConstants.exam_setted_condition2_1)==null?"100":configMap.get(ConfigKeyConstants.exam_setted_condition2_1).getConfigValue());
        Integer exam_setted_condition2_2=Integer.parseInt(configMap.get(ConfigKeyConstants.exam_setted_condition2_2)==null?"1":configMap.get(ConfigKeyConstants.exam_setted_condition2_2).getConfigValue());
        Integer exam_setted_condition3_1=Integer.parseInt(configMap.get(ConfigKeyConstants.exam_setted_condition3_1)==null?"100":configMap.get(ConfigKeyConstants.exam_setted_condition3_1).getConfigValue());
        Integer exam_setted_condition3_2=Integer.parseInt(configMap.get(ConfigKeyConstants.exam_setted_condition3_2)==null?"1":configMap.get(ConfigKeyConstants.exam_setted_condition3_2).getConfigValue());


        Map<Integer,List<AnswerRecord>> anserRecoreMap=answerRecordService.getContestRecordMap();
        Map<Integer,List<ContestRecord>> examMap=contestRecordService.getContestRecordMapByType(0);
        Map<Integer,List<ContestRecord>> contestMap=contestRecordService.getContestRecordMapByType(1);



        List<StudyResultDto> studyResultDtoList=new ArrayList<>();
        PageHelper.startPage(queryStudyResultParam.getPageNo(),queryStudyResultParam.getPageSize());
        StudentExample example=new StudentExample();
        StudentExample.Criteria ctitera=example.createCriteria();
        ctitera.andSchoolIdEqualTo(UserUtil.getSchoolId());
        List<Student> studentList=studentMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(studentList)){
            return PageInfoResult.buildPage();
        }
        for(Student student:studentList){
            StudyResultDto dto=new StudyResultDto();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setTelephone(student.getTelephone());

            List<AnswerRecord> answerReeordList=anserRecoreMap.get(student.getId());
            int right=0;
            int total=answerReeordList.size();
            for(AnswerRecord answerRecord:answerReeordList){
                if(1==answerRecord.getStatus()){//正确的
                   right++;
                }
            }
            float reightS=(float)right/(float)total;

            if(student.getProcess()>=exam_setted_condition1_1&&reightS>exam_setted_condition1_2/100.0){
                dto.setCondition1(1);
            }else{
                dto.setCondition1(0);
            }
            List<ContestRecord> examList=examMap.get(student.getId());
            List<ContestRecord> contestList=contestMap.get(student.getId());
            int condition2=0;
            for(ContestRecord contestRecord:examList){
                if(contestRecord.getScore()>=exam_setted_condition2_1){
                    condition2++;
                }
            }
            if(condition2>=exam_setted_condition2_2){
                dto.setCondition2(1);
            }else{
                dto.setCondition2(0);
            }
            int condition3=0;
            for(ContestRecord contestRecord:contestList){
                if(contestRecord.getScore()>=exam_setted_condition3_1){
                    condition3++;
                }
            }
            if(condition3>=exam_setted_condition3_2){
                dto.setCondition3(1);
            }else{
                dto.setCondition3(0);
            }
            if(student.getContestCount()>0){
                dto.setContestResult(1);
            }else{
                dto.setContestResult(0);
            }

            studyResultDtoList.add(dto);
        }

        return PageInfoResult.buildPageFromList(studentList,studyResultDtoList);
    }
    @Override
    public  void createStudent(Student student){

        studentMapper.insertSelective(student);
    }
    @Override
    public StudentDto getStudentInforById(int id){
        StudentDto studentDto=new StudentDto();
        Student student=studentMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(student,studentDto);
        studentDto.setStatusStr(StudentStatusEnum.getByCode(student.getStatus()).getName());
        studentDto.setClassTypeStr(student.getClassType());
        return studentDto;
    }
}
