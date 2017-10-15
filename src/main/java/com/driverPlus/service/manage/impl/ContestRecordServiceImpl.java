package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.front.ContestRecordMapper;
import com.driverPlus.dao.mapper.manage.AccountMapper;
import com.driverPlus.dao.po.front.ContestRecord;
import com.driverPlus.dao.po.front.ContestRecordExample;
import com.driverPlus.dao.po.manage.Account;
import com.driverPlus.dao.po.manage.AccountExample;
import com.driverPlus.service.manage.AccountService;
import com.driverPlus.service.manage.ContestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class ContestRecordServiceImpl implements ContestRecordService {

    @Autowired
    ContestRecordMapper contestRecordMapper;

    @Override
    public List<ContestRecord> getContestRecordList(){

        ContestRecordExample example=new ContestRecordExample();

        return contestRecordMapper.selectByExample(example);

    }
    @Override
    public Map<Integer,ContestRecord> getContestRecordMap(){

        List<ContestRecord> list=getContestRecordList();
        Map<Integer,ContestRecord> map=new HashMap<>();
        for(ContestRecord contestRecord:list){
            map.put(contestRecord.getStudentId(),contestRecord);
        }


        return map;
    }
    @Override
    public List<ContestRecord> getContestRecordListByType(Integer type ){

        ContestRecordExample example=new ContestRecordExample();
        ContestRecordExample.Criteria criteria=example.createCriteria();
        criteria.andTypeEqualTo(type);

        return contestRecordMapper.selectByExample(example);

    }
    @Override
    public Map<Integer,List<ContestRecord>> getContestRecordMapByType(Integer type){

        List<ContestRecord> list=getContestRecordListByType(type);
        Map<Integer,List<ContestRecord>> map=new HashMap<>();
        for(ContestRecord contestRecord:list){
            List<ContestRecord> contestRecordList=map.get(contestRecord.getStudentId());
            if(!map.containsKey(contestRecord.getStudentId())){
                map=new HashMap<>();
            }
            contestRecordList.add(contestRecord);
            map.put(contestRecord.getStudentId(),contestRecordList);
        }


        return map;
    }
}
