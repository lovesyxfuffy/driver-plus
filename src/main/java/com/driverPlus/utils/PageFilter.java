package com.driverPlus.utils;

import com.driverPlus.dao.dto.FrontPage;
import com.github.pagehelper.PageInfo;


/**
 * Created by yujingyang on 2017/9/26.
 */
public class PageFilter {

    public static FrontPage filter(PageInfo pageInfo){
        FrontPage frontPage = new FrontPage();
        frontPage.setPageNo(pageInfo.getPageNum());
        frontPage.setTotal((int)pageInfo.getTotal());
        frontPage.setPageSize(pageInfo.getPageSize());
        return frontPage;
    }
}
