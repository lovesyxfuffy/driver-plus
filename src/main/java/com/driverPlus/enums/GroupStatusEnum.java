package com.driverPlus.enums;

import com.driverPlus.dao.dto.manage.EnumDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfeng on 2017/10/11.
 */
public enum GroupStatusEnum {
    uncheck(1, "未体检"),
    checked(2, "已通知体检"),
    unpass(3, "体检不合格"),
    waitExam(4, "待考科目一"),
    noticeOne(5, "已通知科目一考试"),
    oneUnPass(6, "科目一不合格"),
    waitTwo(7, "待考科目二"),
    twoUnPass(8, "科目二不合格"),
    waitThree(9, "待考科目三"),
    threeUnPass(10, "科目三不合格"),
    waitFour(11, "待考科目四"),
    noticeFour(12, "已通知科目四考试"),
    fourUnPass(13, "科目四不合格"),
    takeCard(14, "拿证"),
    leaveSchool(15, "退学"),
    special(16, "特殊情况");

    private Integer code;

    private String name;


    GroupStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static GroupStatusEnum getByCode(Integer code){
        for(GroupStatusEnum item: GroupStatusEnum.values()){
            if(code==item.getCode()){
                return item;
            }
        }
        return null;
    }
    public static List<EnumDto> enumDtoList=getGroupStatusList();

    public static List<EnumDto> getGroupStatusList(){

        List<EnumDto> dtoList=new ArrayList<>();
        for(GroupStatusEnum item: GroupStatusEnum.values()){
            EnumDto dto=new EnumDto();
            dto.setId(item.getCode());
            dto.setName(item.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }


}
