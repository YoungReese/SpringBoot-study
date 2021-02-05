package com.ly.test;

import com.ly.pojo.Student;
import com.ly.service.StudentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * liyang 2021-02-04
 *
 * 和主启动项目不在一个包路径下的测试，但是在主启动类的包的子路径下
 *
 * 测试通过！
 */
@SpringBootTest
public class TheNewTest {
    @Autowired
    private StudentService studentService;

    @Test
    void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Student> userList = studentService.list();
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}