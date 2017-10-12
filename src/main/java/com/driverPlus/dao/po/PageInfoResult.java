package com.driverPlus.dao.po;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;


@Slf4j
@Data
@AllArgsConstructor
public class PageInfoResult<E> {
    private static final String pageNo_="pageNo";
    private static final String pageSize_="pageSize";
    private static final String totalCount_="total";
    private static final String totalPageCount_="totalPageCount";

    private Map<String, Integer> page;
    private List<E> pageList;

    public PageInfoResult() {
        this.pageList = Lists.newArrayList();
        this.page = Maps.newHashMap();
        page.put(pageNo_, 1);
        page.put(pageSize_, 20);
        page.put(totalCount_,0);
        page.put(totalPageCount_,0);
    }

    public static <E> PageInfoResult<E> buildPage(){
        return new PageInfoResult();
    }

    public static <E> PageInfoResult buildPageFromList (int currentPage, int pageSize, int totalCount, List<E> list) {
        return new PageInfoResult(currentPage, pageSize, totalCount, list);
    }

    public static <E> PageInfoResult buildPageFromList (int currentPage, int pageSize, List<E> list) {
        return new PageInfoResult(currentPage, pageSize, list);
    }
    public static <E> PageInfoResult buildPageFromList (List pageList, List<E> list) {

        if (pageList == null){
            return PageInfoResult.buildPage();
        }

        try {
            Page page = (Page)pageList;
            return new PageInfoResult(page.getPageNum(), page.getPageSize(), (int)page.getTotal(), list);
        }catch (ClassCastException e){
            log.error("Error convert class. {}", e);
            return PageInfoResult.buildPage();
        }
    }

    public static <E> PageInfoResult buildPageFromList (List<E> pageList) {

        if (pageList == null){
            return PageInfoResult.buildPage();
        }
        Page<E> list;
        try {
            list = (Page) pageList;
            return new PageInfoResult(list.getPageNum(), list.getPageSize(), (int)list.getTotal(), list);
        }catch (ClassCastException e){
            log.error("Error convert class. {}", e);
            return PageInfoResult.buildPage();
        }
    }

    /**
     * 从完整列表当中构造指定分页数据
     *
     * @param currentPage
     * @param pageSize
     * @param list
     */
    @Deprecated
    public PageInfoResult(int currentPage, int pageSize, List<E> list) {

        this.page = Maps.newHashMap();
        if (CollectionUtils.isEmpty(list)) {
            page.put(totalCount_,0);
            page.put(totalPageCount_,0);
            this.pageList = Lists.newArrayList();
        } else {
            int totalCount=list.size();
            int totalPage;
            if (pageSize != 0){
                totalPage = totalCount/pageSize;
                totalPage=totalCount%pageSize==0?totalCount:totalPage+1;
            }else{
                totalPage = 1;
            }
            page.put(totalCount_,totalCount);
            page.put(totalPageCount_,totalPage);
            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = currentPage * pageSize;
            startIndex = startIndex < 0 ? 0 : startIndex;
            endIndex = endIndex > list.size() ? list.size() : endIndex;
            this.pageList = list.subList(startIndex, endIndex);
        }
        page.put(pageNo_, currentPage);
        page.put(pageSize_, pageSize);

    }

    public PageInfoResult(int currentPage, int pageSize, int totalCount, List<E> list) {
        this.page = Maps.newHashMap();
        if (CollectionUtils.isEmpty(list)) {
            this.pageList = Lists.newArrayList();
        } else {
            this.pageList =list;
        }
        int totalPage;
        if (pageSize != 0){
            totalPage = totalCount/pageSize;
            totalPage=totalCount%pageSize==0?totalCount:totalPage+1;
        }else{
            totalPage = 1;
        }
        page.put(totalPageCount_,totalPage);
        page.put(totalCount_,totalCount);
        page.put(pageNo_, currentPage);
        page.put(pageSize_, pageSize);
    }

}