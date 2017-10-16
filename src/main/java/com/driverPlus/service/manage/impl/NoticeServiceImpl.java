package com.driverPlus.service.manage.impl;

import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.dto.manage.NoticeDto;
import com.driverPlus.dao.dto.manage.OrderDto;
import com.driverPlus.dao.mapper.front.NoticeMapper;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.User;
import com.driverPlus.dao.po.manage.Account;
import com.driverPlus.dao.po.manage.Notice;
import com.driverPlus.dao.po.front.NoticeExample;
import com.driverPlus.dao.po.front.Student;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.service.manage.*;
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

    @Autowired
    AccountService accountService;

    @Override
    public List<com.driverPlus.dao.po.front.Notice> getTNoticeList(){
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
            List<com.driverPlus.dao.po.front.Notice> noticeList=new ArrayList<>();
            Date date=new Date();
            for(Student student:studentList){
                if(messageService.noticeToStudentMessage(student.getTelephone(),content)) {
                    com.driverPlus.dao.po.front.Notice notice = new com.driverPlus.dao.po.front.Notice();
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
        List<com.driverPlus.dao.po.front.Notice> noticeList=tNoticeMapper.selectNoticeList(UserUtil.getSchoolId());
        if(CollectionUtils.isEmpty(noticeList)){
           return  PageInfoResult.buildPage();
        }
        for(com.driverPlus.dao.po.front.Notice notice:noticeList){
            NoticeDto dto=new NoticeDto();
            BeanUtils.copyProperties(notice,dto);
            dto.setForUserName(map.get(notice.getForUserId())==null?"":map.get(notice.getForUserId()).getName());

            noticeDtoList.add(dto);
        }

        return PageInfoResult.buildPageFromList(noticeList,noticeDtoList);
    }

    @Override
    public void sendSchoolNotice(NoticeDto dto){
        List<Integer> idList=dto.getIdList();
        Date nowDate=new Date();
        for(Integer id:idList){
            Notice notice=new Notice();
            notice.setForSchoolId(id);
            notice.setName(dto.getName());
            notice.setContent(dto.getContent());
            notice.setAddTime(nowDate);
            notice.setAccountId(UserUtil.getAccountId());
            sNoticeMapper.insertSelective(notice);
        }

    }
    @Override
    public  PageInfoResult<NoticeDto> getSNoticeListWithPage(Integer pageNo,Integer pageSize){

        Map<Integer,School> schoolMap=schoolsService.getSchoolMap();
        Map<Integer,Account>accountMap=accountService.getAccountMap();
        List<NoticeDto> noticeDtoList=new ArrayList<>();
        PageHelper.startPage(pageNo,pageSize);
        com.driverPlus.dao.po.manage.NoticeExample example=new com.driverPlus.dao.po.manage.NoticeExample();
        List<Notice> notices=sNoticeMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(notices)){
            return PageInfoResult.buildPage();
        }
        for(Notice notice:notices){
            NoticeDto dto=new NoticeDto();
            BeanUtils.copyProperties(notice,dto);
            dto.setForSchoolName(schoolMap.get(notice.getForSchoolId())==null?"":schoolMap.get(notice.getForSchoolId()).getName());
            dto.setAccountName(accountMap.get(notice.getAccountId())==null?"":accountMap.get(notice.getAccountId()).getAccountName());

            noticeDtoList.add(dto);
        }
        return PageInfoResult.buildPageFromList(notices,noticeDtoList);
    }

}
