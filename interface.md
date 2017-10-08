driver-plus前后端接口文档
=================
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
     "data": null,
     "message": "操作成功"
     "status":1
  }
  
 5.