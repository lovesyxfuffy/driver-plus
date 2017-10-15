package com.driverPlus.controller.manage;

import com.driverPlus.dao.dto.manage.AgentDto;
import com.driverPlus.dao.dto.manage.EnumDto;
import com.driverPlus.dao.dto.manage.GroupInfoListDto;
import com.driverPlus.dao.dto.manage.StudentResultDto;
import com.driverPlus.dao.po.front.GroupInfo;
import com.driverPlus.dao.po.front.GroupReduction;
import com.driverPlus.dao.po.front.GroupRelation;
import com.driverPlus.dao.po.manage.Agent;
import com.driverPlus.dao.po.manage.ClassWithBLOBs;
import com.driverPlus.dao.po.manage.School;
import com.driverPlus.enums.AgentStatusEnum;
import com.driverPlus.enums.SchoolStatusEnum;
import com.driverPlus.service.manage.*;
import com.driverPlus.utils.ExcelUtil;
import com.driverPlus.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by wangfeng on 2017/10/9.
 */
@RestController
@RequestMapping("/manage/marketing")
@Slf4j
public class MarketingController {

    @Autowired
    private AgentService agentService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/updateAgent",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateAgent(@RequestBody Map<String, String> requestParam){


        Integer id=Integer.parseInt(requestParam.get("id"));
        String name=requestParam.get("realName");
        Integer reduction=Integer.parseInt(requestParam.get("reduction"));
        Integer profileShare=Integer.parseInt(requestParam.get("profitShare"));
        Integer studentCount=Integer.parseInt(requestParam.get("studentCount"));

        Agent agent=new Agent();
        agent.setId(id);
        agent.setUpdateTime(new Date());
        agent.setRealName(name);
        agent.setProfitShare(profileShare);
        agent.setStudentCount(studentCount);
        agent.setReduction(reduction);


        agentService.updateAgentById(agent);

        return WebUtil.success("操作成功");
    }

    @RequestMapping(value = "/stopAgent",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> stopAgent(@RequestParam List<Integer> idList){


          for(Integer id:idList){
              Agent agent=new Agent();
              agent.setId(id);
              agent.setStatus(AgentStatusEnum.closed.getCode());
              agentService.updateAgentById(agent);
          }

        return WebUtil.success("操作成功");
    }

    @RequestMapping(value = "/addAgent",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addAgent(@RequestBody Agent agentPo){


        Agent agent=agentService.addAgent(agentPo);
        EnumDto enumDto=new EnumDto();
        enumDto.setId(agent.getId());

        return WebUtil.result(enumDto);
    }

    @RequestMapping(value = "/getAgentList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getAgentList(@RequestBody AgentDto agentDto){

        return WebUtil.result(agentService.searchAgentList(agentDto));
    }

    @RequestMapping(value = "/getAgentStudentList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getAgentStudentList(@RequestBody Map<String, Integer> requestParam){

        Integer agentId=requestParam.get("agentId");
        Integer pageNo=requestParam.get("pageNo");
        Integer pageSize=requestParam.get("pageSize");

        return WebUtil.result(studentService.searchStudentByAgentIdWithPage(agentId,pageNo,pageSize));
    }

    @RequestMapping(value = "/exportAgentStudentListExcel",method = RequestMethod.GET)
    public ResponseEntity<byte[]>  exportAgentStudentListExcel(@RequestParam Integer agentId)throws Exception{

        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();

        fieldMap.put("realName","学员姓名");
        fieldMap.put("idcard","身份证");
        fieldMap.put("telephone","手机号");
        fieldMap.put("classTypeStr","驾照类型");
        fieldMap.put("price","学费");
        fieldMap.put("addTime","添加时间");

        try{
            List<StudentResultDto> list=studentService.searchStudentByAgentId(agentId);
            XSSFWorkbook xSSFWorkbook= ExcelUtil.exportExcel(fieldMap,list);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            xSSFWorkbook.write(out);
            HttpHeaders headers = new HttpHeaders();
            String fileName = new String("订单.xlsx".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            ResponseEntity<byte[]> filebyte = new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
            out.close();
            return filebyte;
        } catch (InvocationTargetException e) {
            e.printStackTrace();

            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }
    @RequestMapping(value = "/getClassList",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getClassList(@RequestBody Map<String, Integer> requestParam){

        Integer pageNo=requestParam.get("pageNo");
        Integer pageSize=requestParam.get("pageSize");

        return WebUtil.result(classService.getClassListWithPage(pageNo,pageSize));
    }

    @RequestMapping(value = "/addClass",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addClass(@RequestBody ClassWithBLOBs classPo){

        classService.addClass(classPo);

        return WebUtil.success("操作成功");
    }
    @RequestMapping(value = "/getGroupInfo",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getGroupInfo(){

        List<GroupInfo> groupInfoList=groupService.getGroupInfoList();
        Map<Integer,List<GroupReduction>> reductionMap=groupService.getGroupReductionMap();
        List<GroupRelation> groupRelationList=groupService.getGroupRelationList();
        Map<Integer,Integer> countStudentMap=groupService.getGroupCountByStudent(groupRelationList);
        Map<Integer,Integer> countGroupMap=groupService.getGroupCountByOwer(groupRelationList);

        List<GroupInfoListDto> listDtoList=new ArrayList<>();
        for(GroupInfo groupInfo:groupInfoList){
            GroupInfoListDto dto=new GroupInfoListDto();
            BeanUtils.copyProperties(groupInfo,dto);
            dto.setStudentCount(countStudentMap.get(groupInfo.getId())==null?0:countStudentMap.get(groupInfo.getId()));
            dto.setGroupCount(countGroupMap.get(groupInfo.getId())==null?0:countGroupMap.get(groupInfo.getId()));
            dto.setReductionList(reductionMap.get(groupInfo.getId())==null?new ArrayList<GroupReduction>():reductionMap.get(groupInfo.getId()));

            listDtoList.add(dto);
        }

        return WebUtil.result(listDtoList);
    }
    @RequestMapping(value = "/updateGroupInfo",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateGroupInfo(@RequestBody GroupInfoListDto groupInfoListDto){

        groupService.updateGroupInfo(groupInfoListDto);

        return WebUtil.success("操作成功");
    }
    @RequestMapping(value = "/getGroupOwnerEnum",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getGroupOwnerEnum(){

        return WebUtil.result(groupService.getGroupOwnerEnum());
    }


}
