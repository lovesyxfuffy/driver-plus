package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.dto.manage.NoticeDto;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.front.Notice;
import com.driverPlus.dao.po.manage.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface NoticeService {

    List<Notice> getTNoticeList();

    List<com.driverPlus.dao.po.manage.Notice> getSNoticeList();

    String sendStudentNotice(List<Integer> studentIdList,String content,String name) throws Exception;

    PageInfoResult<NoticeDto> getNoticeListWithPage(Integer pageNo,Integer pageSize);

    void sendSchoolNotice(NoticeDto dto);

    PageInfoResult<NoticeDto> getSNoticeListWithPage(Integer pageNo,Integer pageSize);


}
