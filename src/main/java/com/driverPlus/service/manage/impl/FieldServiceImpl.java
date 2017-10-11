package com.driverPlus.service.manage.impl;

import com.driverPlus.dao.mapper.manage.FieldMapper;
import com.driverPlus.dao.po.manage.Field;
import com.driverPlus.dao.po.manage.FieldExample;
import com.driverPlus.dao.po.manage.OrderExample;
import com.driverPlus.service.manage.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangfeng on 17/10/9.
 */
@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    FieldMapper fieldMapper;

    @Override
    public List<Field> getFieldList() {
        FieldExample example = new FieldExample();

        return fieldMapper.selectByExample(example);
    }
}
