package Utils;

import entity.Student;

import java.util.Optional;

/**
 * @Description:
 * @author: daimin
 * @date: Create in 11:23 2019/11/6
 */
public class Utils {
    public static Optional<Student> getOptionalStudent(Student student) {
        return Optional.ofNullable(student);
    }
}
