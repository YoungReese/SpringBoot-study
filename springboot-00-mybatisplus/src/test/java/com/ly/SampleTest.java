package com.ly;

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
 * 经过反复测试，对于本项目的springboot单元测试，仅使用@SpringBootTest即可
 * 然后单元测试类和主启动类在相同的包路径下
 *
 * 测试通过！
 */
@SpringBootTest
public class SampleTest {

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
