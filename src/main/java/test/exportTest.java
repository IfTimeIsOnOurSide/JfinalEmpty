package test;

import excel.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:  excel导出工具测试
 * @author: daimin
 * @date: Create in 14:18 2019/8/29
 */
public class exportTest {
    public static void main(String[] args) {
        String title = "excel表头";
        String[] rowsName = new String[]{"序号", "姓名", "性别", "年龄", "班级"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        String excelName = "sheet";
        for (int i = 0; i < 2; i++) {
            excelName += i;
            HSSFSheet sheet = workbook.createSheet(excelName);
            List<Object[]> dataList = getDataList(rowsName.length);
            ExcelUtils ex = new ExcelUtils(title, rowsName, dataList);
            ex.exportExcel(workbook, sheet, i, excelName);
        }
    }

    //导出数据集
    public static List<Object[]> getDataList(int length) {
        List<Object[]> dataList = new ArrayList<>(length);
        Map<String, Object> dataMap;
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataMap = new HashMap<>();
            dataMap.put("column", i);
            dataMap.put("name", "戴敏");
            dataMap.put("sex", "女");
            dataMap.put("age", "24");
            dataMap.put("class", "三年二班");
            dataMapList.add(dataMap);
        }
        Object[] objs = new Object[length];
        for (int k = 0; k < dataMapList.size(); k++) {
            Map data = dataMapList.get(k);
            objs[0] = data.get("column");
            objs[1] = data.get("name");
            objs[2] = data.get("sex");
            objs[3] = data.get("age");
            objs[4] = data.get("class");
            dataList.add(objs);
        }

        return dataList;
    }
}
