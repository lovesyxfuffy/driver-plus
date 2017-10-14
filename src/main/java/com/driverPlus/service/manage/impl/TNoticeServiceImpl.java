package com.driverPlus.service.manage.impl;

import com.driverPlus.Auth.UserUtil;
import com.driverPlus.dao.mapper.front.NoticeMapper;
import com.driverPlus.dao.po.front.Notice;
import com.driverPlus.dao.po.front.NoticeExample;
import com.driverPlus.service.manage.TNoticeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class TNoticeServiceImpl implements TNoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> getNoticeList(){
        NoticeExample example = new NoticeExample();

        return noticeMapper.selectByExample(example);
    }


}
