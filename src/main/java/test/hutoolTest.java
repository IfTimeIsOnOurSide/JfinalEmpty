package test;

import cn.hutool.core.date.DateUtil;
import entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Hutool工具类测试
 * @author: daimin
 * @date: Create in 10:23 2019/11/12
 */
public class hutoolTest {
    public static void main(String[] args) {
        Student stu0 = new Student("陈钊", 25, "F");
        Student stu1 = new Student("陈钊", 24, "F");
        Student stu2 = new Student("陈钊", 23, "F");

        /**
         * Student 对象类似 JcjgContractFilingBean对象
         */
        List<Student> list = new ArrayList<Student>();
        list.add(stu0);
        list.add(stu1);
        list.add(stu2);

        /** List<Map> **/
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map;
        for (Student stu : list) {
            map = new HashMap<>(16);
            map.put("Name", stu.getName());
            map.put("Age", stu.getAge());
            map.put("Gender", stu.getGander());
            maps.add(map);
        }
    }
}
