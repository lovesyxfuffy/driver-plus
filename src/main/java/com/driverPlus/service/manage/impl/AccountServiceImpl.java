package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.manage.AccountMapper;
import com.driverPlus.dao.mapper.manage.SchoolMapper;
import com.driverPlus.dao.po.manage.Account;
import com.driverPlus.dao.po.manage.AccountExample;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.dao.po.manage.SchoolExample;
import com.driverPlus.service.manage.AccountService;
import com.driverPlus.service.manage.SchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<Account> getAccountList(){

        AccountExample example=new AccountExample();

        return accountMapper.selectByExample(example);

    }
    @Override
    public Map<Integer,Account> getAccountMap(){

        List<Account> list=getAccountList();
        Map<Integer,Account> map=new HashMap<>();
        for(Account account:list){
            map.put(account.getId(),account);
        }


        return map;
    }
}
