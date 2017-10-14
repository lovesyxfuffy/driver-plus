package com.driverPlus.service.manage.impl;

import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.dto.manage.NoticeDto;
import com.driverPlus.dao.dto.manage.OrderDto;
import com.driverPlus.dao.mapper.front.NoticeMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.Notice;
import com.driverPlus.dao.po.front.NoticeExample;
import com.driverPlus.dao.po.front.Student;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.service.manage.MessageService;
import com.driverPlus.service.manage.NoticeService;
import com.driverPlus.service.manage.SchoolsService;
import com.driverPlus.service.manage.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper tNoticeMapper;

    @Autowired
    com.driverPlus.dao.mapper.manage.NoticeMapper sNoticeMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    MessageService messageService;

    @Autowired
    SchoolsService schoolsService;

    @Override
    public List<Notice> getTNoticeList(){
        NoticeExample example = new NoticeExample();

        return tNoticeMapper.selectByExample(example);
    }
    @Override
    public List<com.driverPlus.dao.po.manage.Notice> getSNoticeList(){
        com.driverPlus.dao.po.manage.NoticeExample example = new com.driverPlus.dao.po.manage.NoticeExample();
        com.driverPlus.dao.po.manage.NoticeExample.Criteria criteria=example.createCriteria();
        criteria.andForSchoolIdEqualTo(UserUtil.getSchoolId());

        return sNoticeMapper.selectByExample(example);
    }
    @Override
    public String sendStudentNotice(List<Integer> studentIdList,String content,String name) throws Exception{

        School school=schoolsService.getSchoolById(UserUtil.getSchoolId());
        Integer mssageCount=school.getSmsCount();
        if(mssageCount-studentIdList.size()>=0){
            List<Student> studentList=studentService.getStudentListById(studentIdList);
            List<Notice> noticeList=new ArrayList<>();
            Date date=new Date();
            for(Student student:studentList){
                if(messageService.noticeToStudentMessage(student.getTelephone(),content)) {
                    Notice notice = new Notice();
                    notice.setAddTime(date);
                    notice.setName(name);
                    notice.setContent(content);
                    notice.setForUserId(student.getUserId());
                    boolean noticeFlag=messageService.noticeToStudentMessage(student.getTelephone(),name+":"+content);
                    if(noticeFlag){
                        noticeList.add(notice);
                        mssageCount--;
                    }
                }
            }
            School school2=new School();
            school2.setId(UserUtil.getSchoolId());
            school2.setSmsCount(mssageCount);
            schoolsService.updateSchoolById(school2);
            tNoticeMapper.insertNoticeList(noticeList);
            return "通知成功";
        }else{
            return "您的短信余量不足,请充值";
        }

    }
    @Override
    public PageInfoResult<NoticeDto> getNoticeListWithPage(Integer pageNo,Integer pageSize){

        Map<Integer,Student> map=studentService.getStudentMap();
        List<NoticeDto> noticeDtoList=new ArrayList<>();
        PageHelper.startPage(pageNo,pageSize);
        List<Notice> noticeList=tNoticeMapper.selectNoticeList(UserUtil.getSchoolId());
        if(CollectionUtils.isEmpty(noticeList)){
           return  PageInfoResult.buildPage();
        }
        for(Notice notice:noticeList){
            NoticeDto dto=new NoticeDto();
            BeanUtils.copyProperties(notice,dto);
            dto.setForUserName(map.get(notice.getForUserId())==null?"":map.get(notice.getForUserId()).getName());

            noticeDtoList.add(dto);
        }

        return PageInfoResult.buildPageFromList(noticeList,noticeDtoList);
    }

}
