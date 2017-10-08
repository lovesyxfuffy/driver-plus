driver-plus前后端接口文档
=================
一.订单管理
1.当日概览接口(查今日订单总数,已确认订单数,待确认订单数,取消订单数,订单总金额,各支付方式金额)

url:/manage/Orders/getTodayTotalAndOrders

method:post

request:

{
  "payMentCode":"",//支付方式code
  "locationCode":"",//场地code
  "package":"",//套餐code
  "driverTypeCode":"",//驾照类型code
  "page": { //每页条数，页码
          "pageNo": 1,
          "pageSize": 20,
      }

}

response:

{
    "data":{
            "totalOrders": 123,//今日订单总数
            "confirmOrders": 1, //已确认订单总数
            "waitConfirmOrders": 2,//未确认订单总数
            "cancelOrders":23 //取消订单
            "orderAmount":189.89,//订单总金额 
            "payList":{          //各支付方式金额
               "支付宝":129.90,
               "微信支付":290.90
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
                        "status",""
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

2.查看学员信息接口(可按照学员姓名,身份证号,手机号,驾照类型进行搜索)
url:/manage/Orders/getTodayTotalAndOrders

method:post

request:

{
  "name":"",//学员姓名
  "idCard":"",//身份证号
  "phone":"",//手机号
  "driverTypeCode":""//驾照类型code,暂无驾照类型字段
  "page": { //每页条数，页码
          "pageNo": 1,
          "pageSize": 20,
      }

}

response:

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

3.导出订单信息

url : "/manage/Orders/download"
method : POST
request: 
{
      "payMentCode":"",//支付方式code
      "locationCode":"",//场地code
      "package":"",//套餐code
      "driverTypeCode":"",//驾照类型code
      "idList":[{},{}]  
     
  }
  
response:

{
    "data":{
      },
    "status":1
 }

 4.导入订单信息
 url : "/manage/Orders/download"
 method : POST
 request: 
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
   
 response:
                       
                       {
                          "data":{
                             "data": null,
                             "message": "操作成功"
                           },
                           "status":1
                           }  
  
 二.学员管理 
 5.分组管理查询接口
 
 url:/manage/Groups/getTotalStudentsNumByGroup
 
 method:post
 
 request:
 
 {
 
 }
 
 response:
 
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
 
 6.查分组学员信息
   url:/manage/Groups/getStudentsByGroupsWithPage
   
   method:post
   
   request:
   
   {
     "groupCode":"",//支付方式code
     "page": { //每页条数，页码
             "pageNo": 1,
             "pageSize": 20,
         }
   
   }
   
   response:
   
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
 6.修改学员信息(姓名,手机号,身份证号)
   
  url:/manage/Groups/updateStudentBaseInfo
      
  method:post
      
  request:
      
      {
        "name":"",//姓名    //只修改学员表
        "phone":"",//手机号
        "idCard":"",//身份证号
        "id":12 //学员ID
      }
      
  response:
                        
                        {
                           "data":{
                              "data": null,
                              "message": "操作成功"
                            },
                            "status":1
                            }  
          
 7.修改学员状态
    
   url:/manage/Groups/updateStudentStatus
       
   method:post
       
   request:
       
       {
         "groupCode":"",//姓名
         "id":[{12},{11}] //学员ID
       }
       
   response:
                         
                         {
                            "data":{
                               "data": null,
                               "message": "操作成功"
                             },
                             "status":1
                             }          
           
 8.修改学员状态
     
    url:/manage/Groups/updateStudentStatus
        
    method:post
        
    request:
        
        {
          "groupCode":"",//姓名
          "id":[{12},{11}] //学员ID
        }
        
    response:
                          
                          {
                             "data":{
                                "data": null,
                                "message": "操作成功"
                              },
                              "status":1
                              }             
            
 9.修改组名称(修改状态名称)
      
     url:/manage/Groups/updateGroupName
         
     method:post
         
     request:
         
         {
           "groupCode":"",//状态code
           "groupName":"" //状态名称
         }
         
     response:
                           
                           {
                              "data":{
                                 "data": null,
                                 "message": "操作成功"
                               },
                               "status":1
                               }   
 10.添加分组名称(添加状态值)
       
      url:/manage/Groups/addGroupStatus
          
      method:post
          
      request:
          
          {
            "groupCode":"",//状态code
            "groupName":"" //状态名称
          }
          
      response:
                            
                            {
                               "data":{
                                  "data": null,
                                  "message": "操作成功"
                                },
                                "status":1
                                }   
                
 11.
    
                                 
                                 
 12.
     
                                 
                                 
 13.                                
 
 
 
 
 
 
 三.理论学习管理
 14.学员测评完成情况展示
 url:/manage/Orders/getTodayTotalAndOrders
 
 method:post
 
 request:
 
 {
   "groupCode":"",//分组状态值
   "name":"",//学员姓名
   "idCard":"",//身份证号
   "phone":"",//手机号
   "driverTypeCode":""//驾照类型code,暂无驾照类型字段
   "page": { //每页条数，页码
           "pageNo": 1,
           "pageSize": 20,
       }
 
 }
 
 response:
 
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
                         "passStatus":""//完成测评情况
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
 
 
 
 15.批量开启关闭学员的"远程测考"权限
      
     url:/manage/Groups/openAndCloseRemoteCepingPermission
         
     method:post
         
     request:
         
         {
           "permission":"0",//0-关闭远程测评权限,1-开启远程测评权限
           "id":[{12},{11}] //学员ID
         }
         
     response:
         
         {
             "data": null,
             "message": "操作成功"
             "status":1
             }                                   
 16.测评设置接口
   
     url:/manage/Groups/setCeping
            
        method:post
            
        request:
            
            {
              "allQuestionTimes":"100",//理论练习所有习题的次数比例
              "right":"75",//正确率
              "oneScore":"95",//模拟考试分数
              "oneScoreTimes":"5",//达到次数
              "twoScore":"96",//远程测评分数
              "twoScoreTimes":"3"//达到次数
            }
            
        response:
                              
                              {
                                 "data":{
                                    "data": null,
                                    "message": "操作成功"
                                  },
                                  "status":1
                                  }  
            
             
                
 四.营销管理

  17. 展示现有组团学车的信息
    url:/manage/Market/getGroupDetailInfo
                
    method:post
                
    request:
                
     {
      
     }
                
     response:
                
     {
       "data":{
              "reductionList":[{
                 "id":1,
                 "studentCount":20,
                 "reduction":150,
                 "schoolId":1
                 },{
                 }],
                 
                 "id",
                 "content",
                 "strategy":"75",
                 "deadline":"95"
                 },
       "status":1          
     }   
  18.对组团信息进行修改
     url:/manage/Market/setGroupInfo
                     
         method:post
                     
         request:
                     
          {
          "reductionList":[{
                   "id":1,
                   "studentCount":20,
                   "reduction":150,
                   "schoolId":1
                   },{
                   }],
                                 
                   "id",
                   "content",
                   "strategy":"75",
                   "deadline":"95"
           
          }
                                          
          response:
                      
                      {
                         "data":{
                            "data": null,
                            "message": "操作成功"
                          },
                          "status":1
                          }   
                          
  19.小程序信息管理接口
  url:/manage/Market/getConfigInfoByType
                  
      method:post
                  
      request:
                  
       {
         "type":"wechatApp"//config的type
        
       }
                  
       response:
                  
       {
         "data":{
                "configMap":{
                   "key1":"[{"value":"value1",
                             "name":"name1"},
                             {"valee":"value2",
                              "name":"name2"
                             }
                             ]",
                   "key2":"[{},{}]",
                   }
                 },
         "status":1          
       }   
  20.设置小程序信息
    url:/manage/Market/setConfigInfoByType
                    
        method:post
                    
        request:
                    
         {
           "type":"wechatApp"//config的type
           "key":"key1",
           "value":"value1",
           "name":"name1"
          
         }
                    
          response:
                               
                               {
                                  "data":{
                                     "data": null,
                                     "message": "操作成功"
                                   },
                                   "status":1
                                   }   
  
  21.代理管理接口
  
                          
                          
                          