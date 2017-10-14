package com.driverPlus.service.manage;

import com.driverPlus.dao.dto.manage.ServiceRecordDto;
import com.driverPlus.dao.po.PageInfoResult;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.dao.po.manage.Service;

import java.util.List;

/**
 * Created by wangfeng on 17/10/9.
 */
public interface ServiceService {

    void updateServiceById(Service service);

    PageInfoResult<ServiceRecordDto> getServceiRecordList(Integer pageNo, Integer pageSize);

    List<ServiceRecordDto> getServceiList();
}
