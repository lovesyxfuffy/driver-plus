package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.front.AnswerRecordMapper;
import com.driverPlus.dao.po.front.AnswerRecord;
import com.driverPlus.dao.po.front.AnswerRecordExample;
import com.driverPlus.service.manage.AnswerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class AnswerRecordServiceImpl implements AnswerRecordService {

    @Autowired
    AnswerRecordMapper answerRecordMapper;

    @Override
    public List<AnswerRecord> getAnswerRecordList(){

        AnswerRecordExample example=new AnswerRecordExample();

        return answerRecordMapper.selectByExample(example);

    }
    @Override
    public Map<Integer,List<AnswerRecord>> getContestRecordMap(){

        List<AnswerRecord> list=getAnswerRecordList();
        Map<Integer,List<AnswerRecord>> map=new HashMap<>();
        for(AnswerRecord answerRecord:list){
            List<AnswerRecord> answerRecordList=map.get(answerRecord.getStudentId());
            if(!map.containsKey(answerRecord.getStudentId())){
                answerRecordList=new ArrayList<>();
            }
            answerRecordList.add(answerRecord);
            map.put(answerRecord.getStudentId(),answerRecordList);
        }


        return map;
    }

}
