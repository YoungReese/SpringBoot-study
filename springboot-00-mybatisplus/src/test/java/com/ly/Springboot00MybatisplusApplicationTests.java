package com.ly;

import com.ly.dao.StudentMapper;
import com.ly.pojo.Student;
import com.ly.service.StudentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * liyang 2021-02-04
 * springboot集成mybatis-plus测试
 *
 * 测试通过！
 *
 * 参考资料：
 * https://baomidou.com/guide/quick-start.html#%E6%B7%BB%E5%8A%A0%E4%BE%9D%E8%B5%96
 * https://www.jianshu.com/p/198647219592
 */

@SpringBootTest
class Springboot00MybatisplusApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Student> userList = studentMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }


    @Autowired
    private StudentService studentService;

    @Test
    void testSelectAll() {
        System.out.println(("----- selectAll method test ------"));
        List<Student> userList = studentService.list();
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
