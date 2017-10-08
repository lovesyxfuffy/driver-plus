package com.driverPlus.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yujingyang on 2017/8/3.
 */
public class ExcelUtil {

    /**
     * 生成Excel的方法
     *
     * @param keyColumnMap 需要生成Excel的list的列的LinkedHashMap
     *                     <p>
     *                     例如:
     *                     LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
     *                     linkedHashMap.put("employeeName","姓名");
     *                     linkedHashMap.put("employeeId","id");
     * @param templateList 生成Excel表体需要的实体类 实体类可以有多余的属性 会按照 keyColumnMap 参数去掉多余属性
     *                     要求对应的DTO有 首字母大写的合乎规范的get方法
     *                     例如 属性name->getName(),subList->getSubList()
     * @return XSSFWorkbook类型 使用方法
     * XSSFWorkbook workBook = ExcelUtil.exportExcel(linkedHashMap, tmp);
     * OutputStream ops = new OutPutStream();//springmvc中 httpServletRequest.getOutPutStream();
     * workBook.write(ops);
     * ops.flush();
     * ops.close();
     */

    public static XSSFWorkbook exportExcel(LinkedHashMap<String, String> keyColumnMap, List templateList) throws InvocationTargetException, IllegalAccessException {
        //创建excel工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        //创建第一行
        Row row = sheet.createRow(0);
        Cell cell;
        //插入第一行数据
        int index = 0;
        List<String> keyArray = new ArrayList<>();
        for (Map.Entry<String, String> entry : keyColumnMap.entrySet()) {
            cell = row.createCell(index++);
            cell.setCellValue(entry.getValue());
            keyArray.add(entry.getKey());
        }
        //追加数据
        int indexRow = 1;
        for (Object tmp : templateList) {
            Row nextRow = sheet.createRow(indexRow++);
            Cell cell0;
            int columnIndex = 0;
            for (String key : keyArray) {
                try {
                    cell0 = nextRow.createCell(columnIndex++);
                    Object value = tmp.getClass().getMethod("get" + toFirstUpperCase(key)).invoke(tmp);
                    if (value != null)
                        cell0.setCellValue(String.valueOf(value));
                    else
                        cell0.setCellValue("");

                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }

    private static String toFirstUpperCase(String value) {
        char[] cs = value.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException {
//        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
//        linkedHashMap.put("employeeName", "姓名");
//        linkedHashMap.put("employeeId", "id");
//
//        AppGroupEmployeeRelation appGroupEmployeeRelation = new AppGroupEmployeeRelation();
//        appGroupEmployeeRelation.setEmployeeName("于景洋");
//        appGroupEmployeeRelation.setEmployeeId("2029757");
//
//        AppGroupEmployeeRelation appGroupEmployeeRelation2 = new AppGroupEmployeeRelation();
//        appGroupEmployeeRelation2.setEmployeeName("于景洋2");
//        appGroupEmployeeRelation2.setEmployeeId("20297572");
//
//        List<AppGroupEmployeeRelation> tmp = new ArrayList<>();
//        tmp.add(appGroupEmployeeRelation);
//        tmp.add(appGroupEmployeeRelation2);
//
//        XSSFWorkbook workBook = ExcelUtil.exportExcel(linkedHashMap, tmp);
//        OutputStream ops = new FileOutputStream(new File("/Users/yujingyang/codes/1.xlsx"));
//        workBook.write(ops);
//        ops.flush();
//        ops.close();
    }
}

