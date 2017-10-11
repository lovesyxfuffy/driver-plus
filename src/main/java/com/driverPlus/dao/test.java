package com.driverPlus.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangfeng on 17/10/11.
 */
public class test {
     public static void main(String args[]){
         Date nowDate=new Date();
         SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
         SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

         String begindate=formatter1.format(nowDate);
         String enddate=formatter2.format(nowDate);
         System.out.println(begindate);
         System.out.println(enddate);
     }
}
