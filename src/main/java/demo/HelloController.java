package demo;

import Utils.Utils;
import com.jfinal.core.Controller;
import entity.Student;

import java.sql.SQLOutput;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:  JFinal 框架学习
 * @author: daimin
 * @date: Create in 11:01 2019/4/26
 */
public class HelloController extends Controller {

    public void index() {
        renderText("Hello, JFinal!");
    }

    /**
     * Lambda表达式：允许把函数作为一个方法的参数传递进方法中
     */
    public void lambdaTest() {
        List<String> list = Arrays.asList("你", "是", "猪", "吗", "?", "猪悟能");
//        list.forEach(System.out::println);


        //条件过滤
//        list.stream().filter((str) -> str.contains("猪")).forEach(System.out::println);

        //条件组合过滤 (条件并列)
        Predicate condition1 = (str) -> (((String)str).contains("猪"));
        Predicate condition2 = (str) -> (((String)str).length() == 3);
        list.stream().filter(condition1.and(condition2)).forEach(System.out::println);
        renderText("Lambda表达式学习");
    }

    /**
     * Stream学习
     */
    public void streamTest() {
        //生成Stream  of(长度固定)/generate(随机数和常量)
        Stream<String> strStream = Stream.of("我知道你在与我相遇的路上");
        Stream<String> str0Stream = Stream.of("王荻", "王荻");
        Stream.concat(strStream, str0Stream).distinct().forEach(System.out::println);
//        Stream.of("a", "b", "c").map(item -> item.toUpperCase()).forEach(System.out::println);
//        Stream.of(1, 2, 3).flatMap(item -> Stream.of(item * 10)).forEach(System.out::println);

        List<Integer> list = Stream.of(1, 3, 2).peek(i -> System.out.print("peekCallFirst:" + i + "\n")).sorted().skip(1).collect(Collectors.toList());

        System.out.println(Stream.of(1, 2, 3).reduce(Integer::sum).get());
        System.out.println(Stream.of(10, 20, 30).reduce(2, (a, b) -> a + b));

//        Stream.generate(Math::random).limit(10).forEach(System.out::println);
//        Stream.iterate(1, i -> i+1).limit(10).filter(j -> j >= 6).forEach(System.out::println);
        renderText("Stream学习");
    }

    /**
     * Optional类学习
     */
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

    /**
     * DateTime相关处理
     */
    public void dateTimeTest() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        System.out.println("now: " + now + " year:" + year + " month:" + month + " day:" + day);
        System.out.println("isLeapYear:" + now.isLeapYear() + " dayOfYear:" + now.getDayOfYear() + " dayOfWeek:" + now.getDayOfWeek() + " lengthOfMonth:" + now.lengthOfMonth() + " lengthOfYear:" + now.lengthOfYear());

        //自定义创建日期
        LocalDate oneDay = LocalDate.of(2019, 11, 10);
        LocalDate parseDay = LocalDate.parse("2019-12-24");
        System.out.println("oneDay= " + oneDay + "   parseDay= " + parseDay);

        //比较日期
        System.out.println("比较日期 ：" + oneDay.equals(parseDay));

        //加减日期
        System.out.println("加减日期： " + now.plusDays(2) + "    " + now.minusDays(2));
        //某天的开始时间和当天所在月的第一天
        System.out.println("当天开始时间：" + now.atStartOfDay() + "  当天所在月的第一天： " + now.with(TemporalAdjusters.firstDayOfMonth()));

        //localDateTime
        LocalDateTime localDateTime = LocalDateTime.of(2019, 11, 06, 12, 30, 42);
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        System.out.println("LocalDate: " + now.atTime(LocalTime.MIN) + "   LocalDateTime: " + localDateTime  + " nowDateTime: " + nowLocalDateTime + " 拼接：" + LocalTime.now().atDate(LocalDate.now()));

        //时间戳
        System.out.println("Instant: " + Instant.now() + "    System: " + System.currentTimeMillis());
        //格式化
        String str = nowLocalDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String str0 = nowLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-hh HH:mm:ss"));
        System.out.println("str: " + str + "   str0: " + str0);

        renderText("Java8 DATETIME新特性");
    }
}
