import com.ly.Springboot00MybatisplusApplication;
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
 * 不在主启动类的包路径和其包路径的子路径下的情况
 *
 * 只使用@SpringBootTest测试不通过，报错如下：
 *  java.lang.IllegalStateException:
 *      Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration
 *      or @SpringBootTest(classes=...) with your test
 *
 * 使用@SpringBootTest(classes = Springboot00MybatisplusApplication.class)测试通过！
 */
@SpringBootTest(classes = Springboot00MybatisplusApplication.class)
public class TheAnotherTest {

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
