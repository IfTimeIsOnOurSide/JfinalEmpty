package demo;

import Utils.Utils;
import com.jfinal.core.Controller;
import entity.Student;

import java.util.Optional;

/**
 * @Description:  JFinal 框架学习
 * @author: daimin
 * @date: Create in 11:01 2019/4/26
 */
public class HelloController extends Controller {

    public void index() {
        renderText("Hello, JFinal!");
    }

    public void optionalTest() {
        Student student = new Student("戴敏", 25, "女");
        Optional<Student> stuOptional = Optional.ofNullable(student);
        //map取值
        System.out.println(stuOptional.map(Student::getName));
        //flatMap取值
        System.out.println(stuOptional.flatMap(Utils::getOptionalStudent));

        //orElse  VS orElseGet
        Optional<Student> optionalTest = Optional.empty();
        System.out.println(optionalTest.orElse(new Student("Jack", 28, "男")).toString());
        System.out.println(optionalTest.orElseGet(() -> {
            String name = "Rose";
            String gander = "女";
            int age = 27;
            return new Student(name, age, gander);
        }).toString());

        //ifPresent 对值进行判断然后打印
        Optional.of("老鼠爱大米").ifPresent(System.out::println);
        Optional.of("《相依为命》").ifPresent((val) -> {
            System.out.println("陈小春的" + val);
        });

        //filter 方法过滤
      Optional<String> strOptional = Optional.ofNullable("Java8新特性 Optional类学习").filter((val) -> val.contains("AAAA"));
      System.out.println(strOptional);

        //orElseThrow 抛出特定异常
//        optionalTest.orElseThrow(() -> new RuntimeException("你发生异常了你知道么！"));
        renderText("Optional类学习");
    }
}
