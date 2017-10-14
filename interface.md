driver-plus前后端接口文档
===========

###### 1.获取统计信息接口    完成
url:/manage/order/getStatistic  

method:post

request:
```json
{
  "fieldId":3,
  "classId":5
}
```

response:
```json
{
  "data":{
    "orderCountToday":10,
    "orderConfirmed":11,
    "orderWaitForConfirmed":5,
    "orderCanceled":10,
    "payAmount":30000,
    "onlinePayAmount":10000,
    "offlinePayAmount":20000
  },
  "status":1
}
```

##### 2.获取订单列表(带查询)  完成

url:/manage/order/searchOrderList

method:post

request:
```json
{
    "fieldId":3,
    "classId":5,
    "studentName":"yujingyang",
    "studentIdcard":"123456",
    "telephone":"17621181235",
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
              "id":2,
              "payStatusStr":"已支付",
              "statusStr":"支付完成",
              "studentName":"于景洋",
              "studentIdcard":"123455667",
              "refereeName":"代理人姓名",
              "className":"班型名称",
              "fieldName":"场地名称",
              "addTime":"添加时间"
            },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```
##### 3.场地枚举   完成
url:/manage/order/getFieldEnum

method:post

request:``{}``

response:
```json
{
  "data":[
    {
      "id":3,
      "name":"济南高新区xxxxxx"
    },{},{},{}
  ],
  "status":2
}
```

##### 4.班型枚举   完成
url:/manage/order/getClassEnum

method:post

request:``{}``

response:
```json
{
  "data":[
    {
      "id":3,
      "name":"C2-vip班"
    },{},{},{}
  ],
  "status":2
}
```

##### 4.5 驾照类型枚举  完成
url:/manage/order/getTypeEnum

method:post

request:``{}``

response:
```json
{
  "data":[
    {
      "name":"C2"
    },{},{},{}
  ],
  "status":2
}
```

##### 5.导出Excel   完成
url:/manage/order/exportExcel

method:get

request:
```json
{
  "fieldId":3,
  "classId":5,
  "studentName":"yujingyang",
  "studentIdcard":"123456",
  "telephone":"17621181235"
}
```

response:``file``

##### 6.订单确认 订单取消    完成
url:/manage/order/confirm
url:/manage/order/cancel

method:post

request:
```json
{
  "idList":[1,2,3,4,5,6]
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 7.学员分组(状态)枚举  完成
url:/manage/student/getStatusEnum

method:post

request:``{}``

response:
```json
{
  "data":[
    {
      "id":2,
      "name":"已通知科目一考试"
    },{},{},{}
  ],
  "status":1
}
```

##### 8.添加分组    完成
url:/manage/student/addStatus

method:post

request:
```json
["组名1","组名二"]
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 9.修改分组名称   完成
url:/manage/student/editStatus

method:post

request:
```json
{
  "id":1,
  "name":"奇怪的组名"
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 10.获取学员列表   完成
url:/manage/student/getStudentList

method:post

request:
```json
{
    "name":"1234",
    "idcard":"1111",
    "telephone":"12334455",
    "classType":"",
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
            "id":1,
            "name":"yujingyang",
            "idcard":"111111",
            "telephone":"12345678",
            "classTypeStr":"C",
            "statusStr":"科目一不合格"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```


##### 11.修改学员状态   完成
url:/manage/student/changeStudentStatus

method:post

request:
```json
{
  "id":3,
  "statusId":6
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 12.发送通知   未完成
url:/manage/student/sendNotice

method:post

request:
```json
{
  "idList":[1,2,3,4,5,7],
  "content":"这是发送内容"
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 13.查看消息发送历史 未完成
url:/manage/student/getNoticeList

method:post

request:``{}``

response:
```json
{
  "data":[
    {
      "id":3,
      "forUserName":"于景洋",
      "content":"zxxxxxxx",
      "name":"标题"
    },{},{},{}
  ],
  "status":1
}
```

##### 14.查看驾校短信余量  未完成
url:/manage/student/getLastSms

method:post

request:``{}``

response:
```json
{
  "data":{
    "smsCount":100,
    "smsUsed":30
  },
  "status":1
}
```

##### 15.驾校充值(支付待定) 未完成

##### 16.获取学员学习情况列表 未完成
url:/manage/study/getStudyResultList

method:post

url:

method:post

request:
```json
{
    "name":"1234",
    "idcard":"1111",
    "telephone":"12334455",
    "classType":"",
    "pageNo":1,
    "pageSize":12
}
```

response:
(1代表完成 0代表未完成)
```json
{
  "data":{
    "content":[
        {
            "id":3,
            "condition1":1,
            "condition2":1,
            "condition3":0,
            "contestResult":0,
            "name":"于景洋",
            "telephone":"123456788"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```




##### 17.config录入(各种配置 例如考试条件)  完成
url:/manage/common/setConfig

method:post

request:
```json
{
  "configKey":"xxxx",
  "configName":"123456",
  "configValue":"1111",
  "type":"condition"
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 18.根据类型获取所有配置   完成
url:/manage/common/getConfigByType/{typeName}

method:post

request:``{}``

response:
```json
{
  "data":[
    {
        "configKey":"xxxx",
        "configName":"123456",
        "configValue":"1111",
        "type":"condition",
        "id":5
    },{},{}
  ],
  "status":1
}
```

##### 19.设置远程测考开启关闭  完成
url:/manage/study/setContestStatus

method:post

request:
(0关闭 1开启)
```json
{
  "idList":[1,2,3,4,5,6,7],
  "status":0
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 20.学车套餐列表（班型列表） 完成
url:/manage/marketing/getClassList

method:post

request:
```json
{

    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
          "id":1,
          "name":"banxing",
          "price":30,
          "servicePromise":"xxxxxxxx",
          "priceContent":"xxxxxxxxxx",
          "type":"C1"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```



##### 21.学车套餐添加    完成
url:/manage/marketing/addClass

method:post

request:
```json
{
    "name":"banxing",
    "price":30,
    "servicePromise":"xxxxxxxx",
    "priceContent":"xxxxxxxxxx",
    "typeStr":"C1",
    "fieldId":3
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 22.组团信息展示(文字性内容读config接口)
url:/manage/marketing/getGroupInfo

method:post

request:``{}``

response:
(deadline单位为秒)
```json
{
  "data":[
    {
      "deadline":1223,
      "strategy":"组团攻略",
      "content":"组团说明",
      "studentCount":1,
      "groupCount":3,
      "reductionList":[
        {
          "studentCount":3,
          "reduction":30
        }
      ]
    },{},{}
  ],
  "status":1
}
```

##### 23.组团信息更新
url:/manage/marketing/updateGroupInfo

method:post

request:
```json
{
      "deadline":1223,
      "strategy":"组团攻略",
      "content":"组团说明",
      "studentCount":1,
      "groupCount":3,
      "reductionList":[
        {
          "studentCount":3,
          "reduction":30
        }
      ]
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 24.获取订单列表(以团为单位)

url:/manage/order/searchOrderList

method:post


request:
```json
{
    "ownerId":3,
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
            "id":2,
            "payStatusStr":"已支付",
            "statusStr":"支付完成",
            "studentName":"于景洋",
            "studentIdcard":"123455667",
            "refereeName":"代理人姓名",
            "className":"班型名称",
            "fieldName":"场地名称",
            "addTime":"添加时间"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```

##### 25.获取开团人枚举
url:/manage/marketing/getGroupOwnerEnum

method:post

request:
```json
{

}
```

response:
```json
{
  "data":[
    {
      "id":3,
      "name":"于景洋"
    },{},{},{}
  ],
  "status":1
}
```

##### 26.获取代理列表   完成
url:/manage/marketing/getAgentList

method:post

request:
```json
{
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
          "id":1,
          "realName":"老王代理",
          "reduction":20,
          "profitShare":100,
          "studentCount":10
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```

##### 27.新增代理   完成
url:/manage/marketing/addAgent

method:post

request:
```json
{
    "realName":"老王代理",
    "reduction":20,
    "profitShare":100,
    "studentCount":10
}
```

response:
```json
{
  "data":{
    "id":5
  },
  "status":1
}
```

##### 28.修改代理   完成
url:/manage/marketing/updateAgent

method:post

request:
```json
{
    "id":3,
    "realName":"老王代理",
    "reduction":20,
    "profitShare":100,
    "studentCount":10
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 29.禁用代理    完成
url:/manage/marketing/stopAgent

method:post

request:
```json
{
  "idList":[1,2,3,4,5,6]
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 30.根据代理id查询代理招生列表   完成
url:/manage/marketing/getAgentStudentList

method:post

request:
```json
{
    "agentId":1,
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
          "id":1,
          "realName":"yujingyang",
          "idcard":"111111",
          "telephone":"12345678",
          "classTypeStr":"C",
          "price":1000,
          "addTime":"yyyy-mm-dd"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```


##### 31.导出代理名下学生Excel    完成
url:/manage/marketing/exportAgentStudentListExcel

method:post

request:
```json
{
  "agentId":2
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 32.获取通知    完成(s_notice)
url:/manage/common/getNotice

method:post

request:
```json
{

}
```

response:
```json
{
  "data":[
      {
        "name":"xxxxxxxxxx",
        "content":"xxxxxxxxxxxxxxxxx"
      },{},{}
  ],
  "status":1
}
```

##### 33.获取场地列表   完成
url:/manage/field/getFieldList

method:post

request:
```json
{
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
          "id":1,
          "name":"11111xxxx",
          "addTime":"2017-10-09"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```

##### 34.获取场地详情    完成
url:/manage/field/getField/{fieldId}

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
    "id":3,
    "name":"aaaa",
    "position":"xxxxxxxx",
    "content":"xxxxxxx"
  },
  "status":1
}
```

##### 35.新增场地   完成
url:/manage/field/createField

method:post

request:
```json
{
  "name":"aaaaa",
  "position":"bbbbbbbb",
  "content":"xxxxxxxx"

}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 36.编辑场地   完成
url:/manage/field/updateField

method:post

request:
```json
{
  "id":3,
  "name":"aaaaa",
  "position":"bbbbbbbb",
  "content":"xxxxxxxx"

}
```

response:
```json
{
  "data":{},
  "status":1
}
```



##### 37.获取订单列表(带查询)  

url:/manage/admin/order/searchOrderList

method:post

request:
```json
{
    "fieldId":3,
    "classId":5,
    "studentName":"yujingyang",
    "studentIdcard":"123456",
    "telephone":"17621181235",
    "city":"上海",
    "schoolId":5,
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
          "id":2,
          "payStatusStr":"已支付",
          "statusStr":"支付完成",
          "studentName":"于景洋",
          "studentIdcard":"123455667",
          "refereeName":"代理人姓名",
          "className":"班型名称",
          "fieldName":"场地名称",
          "addTime":"添加时间",
          "schoolName":"xxxx驾校"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```

##### 38.获取驾校列表(带查询)
url:/manage/admin/getSchoolList

method:post

request:
```json
{
    "city":"上海市",
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
          "id":3,
          "name":"xxx驾校",
          "adminName":"",//config
          "adminEmail":"",//config
          "adminJob":"",//config
          "telephone":"1231434514",
          "sms_count":15
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```



##### 39.获取驾校 当日/ 当月 / 总计 报名人数
url:/manage/admin/getSchoolStudentCount

method:post

request:
```json
{
  "schoolId":3
}
```

response:
```json
{
  "data":{
    "day":3,
    "month":5,
    "amount":10
  },
  "status":1
}
```

##### 40.对指定驾校列表发送通知
url:/manage/admin/sendNotice

method:post

request:
```json
{
  "idList":[1,2,3,4,5,6]
}
```

response:
```json
{
  "data":{},
  "status":1
}
```

##### 41.获取通知列表
url:/manage/admin/getNoticeList

method:post

request:
```json
{
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
          "id":3,
          "forSchoolName":"xxxx驾校",
          "accountName":"xxxx管理员",
          "name":"xxxx标题",
          "content":"xxxxxxxxx"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```

##### 42.获取服务列表(不需要分页)
url:/manage/admin/getServiceList

method:post

request:
```json
{

}
```

response:
```json
{
  "data":[
  {
    "id":"2",
    "serviceName":"xxxx服务",
    "price":100,
    "type":"",
    "typeStr":"短信服务"
  }
  ],
  "status":1
}
```

##### 43.获取业务的交易记录
url:/manage/admin/getServiceRecord

method:post

request:
```json
{
    "pageNo":1,
    "pageSize":12
}
```

response:
```json
{
  "data":{
    "content":[
        {
            "id":"2",
            "serviceName":"xxxx服务",
            "price":100,
            "type":"",
            "typeStr":"短信服务",
            "buySchool":"xxx驾校",
            "addTime":"yyyy-mm-dd"
        },{},{},{}
    ],
    "page":{
        "pageNo":1,
        "pageSize":10,
        "total":3
    }
  },
  "status":1
}
```

#### 44.短信内容更新
url:/manage/admin/updateService

method:post

request:
```json
{
  "id":"2",
  "serviceName":"xxxx服务",
  "price":100,
  "type":"sms_service"//写死 sms_service 和 service
}
```

response:
```json
{
  "data":{},
  "status":1
}
```






##### 45.