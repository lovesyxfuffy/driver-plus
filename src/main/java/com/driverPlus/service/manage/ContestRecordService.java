package com.driverPlus.service.manage;

import com.driverPlus.dao.po.front.ContestRecord;
import com.driverPlus.dao.po.manage.Account;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface ContestRecordService {

    List<ContestRecord> getContestRecordList();

    Map<Integer,ContestRecord> getContestRecordMap();

    List<ContestRecord> getContestRecordListByType(Integer type );

    Map<Integer,List<ContestRecord>> getContestRecordMapByType(Integer type);
}
