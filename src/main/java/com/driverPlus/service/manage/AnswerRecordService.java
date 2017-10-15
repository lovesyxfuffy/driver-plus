package com.driverPlus.service.manage;

import com.driverPlus.dao.po.front.AnswerRecord;
import com.driverPlus.dao.po.manage.Account;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface AnswerRecordService {

    List<AnswerRecord> getAnswerRecordList();

    Map<Integer,List<AnswerRecord>> getContestRecordMap();
}
