driver-plus前后端接口文档
=================

##### 1.当日概览接口(查今日订单总数,已确认订单数,待确认订单数,取消订单数,订单总金额,各支付方式金额)/只取当前schoolId数据

url:/manage/Orders/getTodayTotalAndOrders

method:post

request:
```json
 

{
  "payMentCode":"",//支付方式code
  "locationCode":"",//场地code
  "package":"",//套餐code
  "driverTypeCode":"",//驾照类型code
  "page": { //每页条数，页码
          "pageNo": 1,
          "pageSize": 20
      }

}

 
```
response:
```json
 

{
    "data":{
            "totalOrders": 123,//今日订单总数
            "confirmOrders": 1, //已确认订单总数
            "waitConfirmOrders": 2,//未确认订单总数
            "cancelOrders":23, //取消订单
            "orderAmount":189,//订单总金额
            "payList":{          //各支付方式金额
               "支付宝":129,
               "微信支付":290
            },
            "orderList":[{
                        "className":"普通班",//班级
                        "locationName":"上海市长宁区",//场地
                        "agentPhone":"18000000000",//代理人手机号
                        "":"",//预付定金
                        "name":"张三",
                        "idCard":"1221121212121",
                        "phone":"17000000000",
                        "qq":"123123123",
                        "":"",//没有本地暂住证
                        "":"",//无法提供身份证原件
                        "status":1
            }],
            "page":{
                 "pageNo": 1,
                 "pageSize": 10,
                 "totalCount": 320,
                 "totalPageCount": 4
                 }
            },

    "status":1

}

 
```
##### 2.查看学员信息接口(可按照学员姓名,身份证号,手机号,驾照类型进行搜索)
url:/manage/Orders/getTodayTotalAndOrders

method:post

request:
```json
 

{
  "name":"",//学员姓名
  "idCard":"",//身份证号
  "phone":"",//手机号
  "driverTypeCode":"",//驾照类型code, student表中的classType
  "page": { //每页条数，页码
          "pageNo": 1,
          "pageSize": 20
      }

}

 
```
response:
```json
 

{
    "data":{
            "studentList":[{
                        "className":"普通班",//班级
                        "locationName":"上海市长宁区",//场地
                        "agentPhone":"18000000000",//代理人手机号
                        "":"",//预付定金
                        "name":"张三",
                        "idCard":"1221121212121",
                        "phone":"17000000000",
                        "qq":"123123123",
                        "":"",//没有本地暂住证
                        "":""//无法提供身份证原件
            }],
            "page":{
                 "pageNo": 1,
                 "pageSize": 10,
                 "totalCount": 320,
                 "totalPageCount": 4
                 }
            },

    "status":1

}

 
```
##### 3.导出订单信息

url : "/manage/Orders/download"
method : POST
request:
```json
 
{
      "payMentCode":"",//支付方式code
      "locationCode":"",//场地code
      "package":"",//套餐code
      "driverTypeCode":"",//驾照类型code
      "idList":[11,12]

  }

 
```
response:
```json
 

{
    "data":{
      },
    "status":1
 }

  
```
##### 4.导入订单信息
url : "/manage/Orders/download"
method : POST
request:
```json
 
 {
       "studentList":[{
            "className":"普通班",//班级
            "locationName":"上海市长宁区",//场地
            "agentPhone":"18000000000",//代理人手机号
            "":"",//预付定金
            "name":"张三",
            "idCard":"1221121212121",
            "phone":"17000000000",
            "qq":"123123123",
            "":"",//没有本地暂住证
            "":""//无法提供身份证原件
       }]

   }

  
```
response:
```json
 

                       {
                          "data":{
                             "data": null,
                             "message": "操作成功"
                           },
                           "status":1
                           }

  
```
##### 5.分组管理查询接口

url:/manage/Groups/getTotalStudentsNumByGroup

method:post

request:
```json
 

 {

 }

  
```
response:
```json
 

 {
     "data":{
             "list":[{
               "name":"未体检",
               "num":10
             },
             {

             },
             {

             }]
             },

     "status":1

 }

  
```
##### 6.查分组学员信息
url:/manage/Groups/getStudentsByGroupsWithPage

method:post

request:
```json
 

   {
     "groupCode":"",//支付方式code
     "page": { //每页条数，页码
             "pageNo": 1,
             "pageSize": 20
         }

   }

    
```
response:
```json
 

   {
       "data":{
               "studentList":[{
                  "id":12,//学员ID
                  "className":"普通班",//班级
                  "locationName":"上海市长宁区",//场地
                  "agentPhone":"18000000000",//代理人手机号
                  "":"",//预付定金
                  "name":"张三",
                  "idCard":"1221121212121",
                  "phone":"17000000000",
                  "qq":"123123123",
                  "":"",//没有本地暂住证
                  "":"",//无法提供身份证原件
                  "status":"无体检"
                  }],
               "page":{
                    "pageNo": 1,
                    "pageSize": 10,
                    "totalCount": 320,
                    "totalPageCount": 4
                    }
               },

       "status":1

   }
  
```
##### 6.修改学员信息(姓名,手机号,身份证号)

url:/manage/Groups/updateStudentBaseInfo

method:post

request:
```json
 

      {
        "name":"",//姓名    //只修改学员表
        "phone":"",//手机号
        "idCard":"",//身份证号
        "id":12 //学员ID
      }

   
```
response:
```json
 

                        {
                           "data":{
                              "data": null,
                              "message": "操作成功"
                            },
                            "status":1
                            }

  
```

##### 8.修改学员状态

url:/manage/Groups/updateStudentStatus

method:post

request:
```json
 

        {
          "groupCode":"",//姓名
          "id":[12,11] //学员ID
        }

     
```
response:
```json
 

                          {
                             "data":{
                                "data": null,
                                "message": "操作成功"
                              },
                              "status":1
                              }

  
```
##### 9.修改组名称(修改状态名称)

url:/manage/Groups/updateGroupName

method:post

request:
```json
 

         {
           "groupCode":"",//状态code
           "groupName":"" //状态名称
         }

      
```
response:
```json
{
  "data":{
     "data": null,
     "message": "操作成功"
   },
   "status":1
}
  
```
##### 10.添加分组名称(添加状态值)

url:/manage/Groups/addGroupStatus

method:post

request:
```json
 

          {
            "groupCode":"",//状态code
            "groupName":"" //状态名称
          }

       
```
response:
```json
 

{
   "data":{
      "data": null,
      "message": "操作成功"
    },
    "status":1
}

  
```


##### 14.学员测评完成情况展示
url:/manage/Orders/getTodayTotalAndOrders

method:post

request:
```json
 {
   "groupCode":"",//分组状态值
   "name":"",//学员姓名
   "idCard":"",//身份证号
   "phone":"",//手机号
   "driverTypeCode":"",//驾照类型code,暂无驾照类型字段
   "page": { //每页条数，页码
           "pageNo": 1,
           "pageSize": 20
       }

 }

  
```
response:
```json
 

 {
     "data":{
             "studentList":[{
                         "className":"普通班",//班级
                         "locationName":"上海市长宁区",//场地
                         "agentPhone":"18000000000",//代理人手机号
                         "":"",//预付定金
                         "name":"张三",
                         "idCard":"1221121212121",
                         "phone":"17000000000",
                         "qq":"123123123",
                         "passStatus":"通过",//模拟测评完成情况   通过  or 不通过
                         "remoteCepingStatus":"通过" //远程测评 通过 or 不通过  模拟测评只要有一次合格就是通过,否则就都不通过
             }],
             "page":{
                  "pageNo": 1,
                  "pageSize": 10,
                  "totalCount": 320,
                  "totalPageCount": 4
                  }
             },

     "status":1

 }



  
```
##### 15.批量开启关闭学员的"远程测考"权限

url:/manage/Groups/openAndCloseRemoteCepingPermission

method:post

request:
```json
 

         {
           "permission":"0",//0-关闭远程测评权限,1-开启远程测评权限
           "id":[12,11] //学员ID
         }

      
```
response:
```json
 

         {
             "data": null,
             "message": "操作成功",
             "status":1
             }
  
```
##### 16.测评设置接口

url:/manage/Groups/setCeping

method:post

request:
```json
 

            {
              "allQuestionTimes":"100",//理论练习所有习题的次数比例
              "right":"75",//正确率
              "oneScore":"95",//模拟考试分数
              "oneScoreTimes":"5",//达到次数
              "twoScore":"96",//远程测评分数
              "twoScoreTimes":"3"//达到次数
            }

         
```
response:
```json
 

                              {
                                 "data":{
                                    "data": null,
                                    "message": "操作成功"
                                  },
                                  "status":1
                                  }

   
```
##### 17. 展示现有组团学车的信息
url:/manage/Market/getGroupDetailInfo

method:post

request:
```json
 

     {

     }

      
```
response:
```json
 

     {
       "data":{
              "reductionList":[{
                 "id":1,
                 "studentCount":20,
                 "reduction":150,
                 "schoolId":1
                 },{
                 }],
                 "id":2,
                 "content":"xxxxxxxxxxxxxx",
                 "strategy":"75",
                 "deadline":"95"
                 },
       "status":1
     }
   
```
##### 18.对组团信息进行修改
url:/manage/Market/setGroupInfo

method:post

request:
```json
 

          {
          "reductionList":[{
                   "id":1,
                   "studentCount":20,
                   "reduction":150,
                   "schoolId":1
                   },{
                   }],

                   "id":1,
                   "content":"xxxxxxxxxx",
                   "strategy":"75",
                   "deadline":"95"

          }

           
```
response:
```json
 

                      {
                         "data":{
                            "data": null,
                            "message": "操作成功"
                          },
                          "status":1
                          }

   
```
##### 19.小程序信息管理接口(key 唯一)
url:/manage/Market/getConfigInfoByType

method:post

request:
```json
       {
         "type":"wechatApp"//config的type

       }
```
response:
```json
 

       {
         "data":{
                "configPo":[{
                   "id":12,
                   "key":"key1",
                   "value":"value1",
                   "name":"name1",
                   "type":"wechatApp"
                   },
                   {
                    "id":13,
                    "key":"key2",
                    "value":"value2",
                    "name":"name2",
                    "type":"wechatApp"
                   }]
                 },
         "status":1
       }
   
```
##### 20.设置小程序信息
url:/manage/Market/setConfigInfoByType

method:post

request:
```json
 

         {
           "configPoList":[{
           "id":12,
           "key":"key1",
           "value":"value1",
           "name":"name1",
           "type":"wechatApp"//config的type
           },
           {
           "id":13,
           "key":"key2",
           "value":"value2",
           "name":"name2",
           "type":"wechatApp"//config的type
           }]

         }

           
```
response:
```json
 

                               {
                                  "data":{
                                     "data": null,
                                     "message": "操作成功"
                                   },
                                   "status":1
                                   }

   
```
##### 21.代理管理列表显示接口
url:/manage/Market/getAgents

method:post

request:
```json
 

   {
     "realName":"张三",
     "telephone":"1800000000",
     "page": { //每页条数，页码
             "pageNo": 1,
             "pageSize": 20
         }

   }

    
```
response:
```json
 

   {
       "data":{
               "agentList":[{
                           "id":12,
                           "realName":"张三",
                           "telephone":"18000000000",
                           "studentCount":12,
                           "reduction":12,
                           "profitShare":"张三"

               }],
               "page":{
                    "pageNo": 1,
                    "pageSize": 10,
                    "totalCount": 320,
                    "totalPageCount": 4
                    }
               },

       "status":1

   }
    
```
##### 22.修改代理信息接口
url:/manage/Market/setAgent

method:post

request:
```json
 

       {
         "id":12,
         "realName":"张三",
         "telephone":"18000000000",
         "studentCount":12,
         "reduction":12,
         "profitShare":"张三"

       }
        
```
response:
```json
 

                                      {
                                         "data":{
                                            "data": null,
                                            "message": "操作成功"
                                          },
                                          "status":1
                                          }

    
```
##### 23.添加代理信息接口
url:/manage/Market/addAgent

method:post

request:
```json
 

            {

              "realName":"张三",
              "telephone":"18000000000",
              "studentCount":12,
              "reduction":12,
              "profitShare":"张三"

            }


      
```
response:
```json
 

                                    {
                                       "data":{
                                          "data": {
                                              "id":23
                                          },
                                          "message": "操作成功"
                                        },
                                        "status":1
                                        }

   
```
##### 24.禁用代理
url:/manage/Market/forbiddenAgent

method:post

request:
```json
 

               {
                 "id":12

               }
                
```
response:
```json
 

                                              {
                                                 "data":{
                                                    "data": null,
                                                    "message": "操作成功"
                                                  },
                                                  "status":1
                                                  }
   
```
##### 25.根据代理纪录查看代理招生明细列表
                                                                                   
  
  
  
  
  
  
  
##### 26.对优惠层次信息进行修改
url:/manage/Market/setReductionInfo

method:post

request:
```json
 

          {
          "reductionList":[{
                   "id":1,
                   "studentCount":20,
                   "reduction":150,
                   "schoolId":1
                   },{
                   }] 
          }
  ``` 
          
 response:
 ```json
  
 
                                     {
                                        "data":{
                                           "data": {
                                               "id":23
                                           },
                                           "message": "操作成功"
                                         },
                                         "status":1
                                         }
 
    
 ``` 
##### 27.显示已经注册的账号记录列表
      
                          
                          
                          

##### 28.显示已经注册的账号记录列表       
                   
                   
                   
##### 29.查看账号详细情况
                   

##### 30.禁用启用账号
                 
                 
                 
##### 31.添加账号,并设定相应权限接口
               
               
               
##### 32.通知接口            
                 
                 
                 
##### 33.订单管理接口(运营后台)   
url:/manage/Orders/getOrdersNoSchoolId

method:post

request:
```json
 

{
  "schooleId":"",//
  "cityCode":"",//
  "page": { //每页条数，页码
          "pageNo": 1,
          "pageSize": 20
      }

}

 
```
response:
```json
 

{
    "data":{
            "orderList":[{
                        "schoolName":"长安驾校",
                        "cityName":"上海",
                        "className":"普通班",//班级
                        "locationName":"上海市长宁区",//场地
                        "agentPhone":"18000000000",//代理人手机号
                        "":"",//预付定金
                        "name":"张三",
                        "idCard":"1221121212121",
                        "phone":"17000000000",
                        "qq":"123123123",
                        "":"",//没有本地暂住证
                        "":"",//无法提供身份证原件
                        "status":1
            }],
            "page":{
                 "pageNo": 1,
                 "pageSize": 10,
                 "totalCount": 320,
                 "totalPageCount": 4
                 }
            },

    "status":1

}

 
```   
          
##### 34.上传题目(运营后台)  
url:/manage/Questions/uploadQuestions

method:post

request:
```json
 

{
  "cityId":"",
  "questionList":[{
        "id":12,
        "type":"",//
        "content":"题目内容",//
        "chapter":"章节号",//
        "explaination":"题目解释（答案解析）",
        "answerList":[{ 
           "id":12,
           "right":1,
           "content":"ads",
           "number":"12"
        },{
        
        }
        ]
  }]

}

 
```
response:
```json
 
    {
                                  "data":{
                                     "data": null,
                                     "message": "操作成功"
                                   },
                                   "status":1
                                   }


 
```   
      
##### 35.删除题目接口(运营后台) 

url:/manage/Questions/deleteQuestions

method:post

request:
```json
 

{
  "questionIdList":[1,2,3]
  

}

 
```
response:
```json
 
    {
                                  "data":{
                                     "data": null,
                                     "message": "操作成功"
                                   },
                                   "status":1
                                   }


 
``` 

##### 36.修改题目接口(运营后台)  

url:/manage/Questions/updateQuestions

method:post

request:
```json
 

{
  "questionList":[{
        "id":12,
        "type":"",//
        "content":"题目内容",//
        "chapter":"章节号",//
        "explaination":"题目解释（答案解析）",
        "answerList":[{ 
           "id":12,
           "right":1,
           "content":"ads",
           "number":"12"
        },{
        
        }
        ]
  }]

}

 
```
response:
```json
 
    {
                                  "data":{
                                     "data": null,
                                     "message": "操作成功"
                                   },
                                   "status":1
                                   }


 
``` 
           
##### 37.获取注册的驾校列表信息(运营后台)  驾校列表查询接口-针对上线管理的第一个需求,驾校管理的第一个需求,两个接口合二为一,看是否合理?
url:/manage/Schools/getSchools

method:post

request:
```json
 

{
  "status":"1",//1-支付完成,待确认
  "city":"",//
  "page": { //每页条数，页码
          "pageNo": 1,
          "pageSize": 20
      }

}

 
```
response:
```json
 

{
    "data":{
            
            "schoolList":[{
                        "name":"长安驾校",//
                        "locationName":"上海市长宁区",//驾校地址
                        "managerName":"张三",//管理员
                        "managerEmail":"",//管理员邮箱
                        "position":"",//职位
                        "phone":"17000000000",//管理员电话
                        "status":1
            }],
            "page":{
                 "pageNo": 1,
                 "pageSize": 10,
                 "totalCount": 320,
                 "totalPageCount": 4
                 }
            },

    "status":1

}

 
```             
##### 38.审核通过驳回接口(运营后台)-上线管理  
url:/manage/Schools/agreeAndRefuseSchools

method:post

request:
```json
 

{
  "schoolId":2,
  "type":0 //0-拒绝,1-通过

}

 
```
response:
```json
 

  {
                                  "data":{
                                     "data": null,
                                     "message": "操作成功"
                                   },
                                   "status":1
                                   }

 
```         
           
                 