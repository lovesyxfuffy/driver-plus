package com.driverPlus.service.manage;

import com.driverPlus.dao.po.manage.Account;
import com.driverPlus.dao.po.manage.School;

import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface AccountService {

    List<Account> getAccountList();

    Map<Integer,Account> getAccountMap();
}
