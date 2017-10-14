package com.driverPlus.service.manage.impl;

import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.mapper.front.NoticeMapper;
import com.driverPlus.dao.po.front.Notice;
import com.driverPlus.dao.po.front.NoticeExample;
import com.driverPlus.service.manage.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
