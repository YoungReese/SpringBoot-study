SpringBoot



# 1 第一个SpringBoot程序！

环境：

jdk1.8

maven：

springboot：2.3.4

idea 2020.1





两种创建基于maven的springboot项目



方式1：

1、登陆官网https://spring.io/projects/spring-boot#overview

<img src="SpringBoot.assets/image-20201023150523686.png" alt="image-20201023150523686" style="zoom:50%;" />

2、点击[Spring Initializr](https://start.spring.io/) 进入 (`https://start.spring.io/`)

<img src="SpringBoot.assets/image-20201023151220335.png" alt="image-20201023151220335" style="zoom:50%;" />

3、红色箭头是特别需要注意的，方框是填完后直接生成springboot项目

4、打开本地idea，选择项目文件，选择导入（2020.1无需其他操作），等待项目下载依赖！



2.3.4有如下问题，我尝试设置从阿里仓库获取，还是红色！



![image-20201023151619826](SpringBoot.assets/image-20201023151619826.png)

```java
Could not transfer artifact net.bytebuddy:byte-buddy-agent:jar:1.10.14 from/to central (https://repo.maven.apache.org/maven2): Transfer failed for https://repo.maven.apache.org/maven2/net/bytebuddy/byte-buddy-agent/1.10.14/byte-buddy-agent-1.10.14.jar
```





<img src="SpringBoot.assets/image-20201023164943391.png" alt="image-20201023164943391" style="zoom:50%;" />

虽然有包没有导入，但是可以正常运行！





打jar包

<img src="SpringBoot.assets/image-20201023171238818.png" alt="image-20201023171238818" style="zoom:50%;" />

执行jar包

![image-20201023171201215](SpringBoot.assets/image-20201023171201215.png)

结果

<img src="SpringBoot.assets/image-20201023171347301.png" alt="image-20201023171347301" style="zoom:50%;" />



mac上部署的jar，让它结束运行！

```linux
ps aux|grep helloworld-0.0.1-SNAPSHOT.jar

kill -9 24749
```

![image-20201023173438659](SpringBoot.assets/image-20201023173438659.png)





**当我重新打开idea！tmd没有红色了，贼舒服！**



方法2：使用idea，选择Spring Initializr后从官网获取

<img src="SpringBoot.assets/image-20201023203753511.png" alt="image-20201023203753511" style="zoom:50%;" />



<img src="SpringBoot.assets/image-20201023204252470.png" alt="image-20201023204252470" style="zoom:50%;" />



在显示生成banner的网站！

https://www.bootschool.net/ascii





原理初探

在pom.xml中，父工程parent中点击2.3.4.RELEASE，就会看到如下依赖，它是spring-boot的核心依赖！

```
spring-boot-dependencies
```

在spring-boot中引入依赖的时候，不需要指定版本，就是因为对应的springboot中有对应的版本version



启动器，也就是springboot的启动场景

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```

比如：spring-boot-start-web，他就会帮我们自动导入web环境所有的依赖

springboot会将所有的功能场景，都变成一个个的启动器

我们要使用什么功能，我们就找到对应的启动器就可以了`starter`



主程序

```java
// @SpringBootApplication : 标注这个类是一个Springboot的应用
@SpringBootApplication
public class Springboot01HelloworldApplication {
    public static void main(String[] args) {
        // 将springboot应用启动
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }
}
```



@SpringBootApplication里面的两个重要注解：

```java
@SpringBootConfiguration: springboot的配置，本质也是一个spring组件

@EnableAutoConfiguration: 自动配置
```





<img src="SpringBoot.assets/image-20201024003437885.png" alt="image-20201024003437885" style="zoom:50%;" />



 



# 2 SpringBoot配置

application.properties中可以有哪些配置？

可以参见这个文档[Common Application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties)



官方文档配置太多，需要了解原理。

官方推荐yaml文件格式，不建议使用properties文件，所以建议删掉重新建一个（因为peoperties只能存键值对，yaml存储的类型可以是键值对、对象、数组等）

```yaml
# 可以注入到相应的类中，也就是java中不设置值，通过这里注入，和spring中使用xml注入类似
# 普通的k-v
name: liyang

# 对象
student:
  name: liyang
  age: 18

# 对象的行内写法
person: {name: ly, age: 18}

# 数组
pets:
  - cat
  - dog
  - tiger

animals: [bird, lion, dolphin]
```







<img src="SpringBoot.assets/image-20201024105946800.png" alt="image-20201024105946800" style="zoom:50%;" />



Configuring the Annotation Processor

To use the processor, include a dependency on `spring-boot-configuration-processor`.

With Maven the dependency should be declared as optional, as shown in the following example:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```



我们上面采用的yaml方法都是最简单的方式，开发中最常用的；也是springboot所推荐的！那我们来唠唠其他的实现方式，道理都是相同的；写还是那样写；配置文件除了yml还有我们之前常用的properties ， 我们没有讲，我们来唠唠！

【注意】properties配置文件在写中文的时候，会有乱码 ， 我们需要去IDEA中设置编码格式为UTF-8；

<img src="SpringBoot.assets/image-20201024112050871.png" alt="image-20201024112050871" style="zoom:50%;" />



![image-20201024112135623](SpringBoot.assets/image-20201024112135623.png)





其他注入方式

```java
@Component //注册bean
@ConfigurationProperties(prefix = "users")
public class User {
    //直接使用@value
    @Value("${users.name}") // 从配置文件中取值，因为配置的@ConfigurationProperties，可以让其自动装配，因此可以省略
    private String name;
    @Value("#{9 * 2}")     // #{SPEL} Spring表达式
    private int age;
    @Value("男")           // 字面量
    private String sex;

    public User() {
    }

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
```

注意这里，prefix使用users定位yaml文件中的users，因为在META-INF下有一个红框文件，里面的内容有user.name这个字段，它会获取本机的user名字，导致自己的设置值不生效，所以使用users来对应yaml文件中的users，避免出现问题。

<img src="SpringBoot.assets/image-20201024165303114.png" alt="image-20201024165303114" style="zoom:50%;" />	

```json
{
  "properties": [
    {
      "name": "user.name",
      "type": "java.lang.String",
      "description": "Description for user.name."
  }
] }
```

另外，在配置的时候不要重复配置，因为存在优先级问题，所以有些使用@Value设定的会被yaml文件中的设定给覆盖！



yaml支持松散绑定，就是yaml中的first-name可以对应到类中的firstName。

yaml文件内容

```yaml
person:
  name: liyang
  age: 18
  happy: false
  birth: 2002/01/01
  maps: {k1: v1,k2: v2}
  lists:
    - code
    - game
    - music
  dog:
    name: 旺旺
    age: 1

users:
  name: god


student:
  first-name: yang
  last-name: li
```



**对比小结**

@Value这个使用起来并不友好！我们需要为每个属性单独注解赋值，比较麻烦；我们来看个功能对比图

![image-20201024173028102](SpringBoot.assets/image-20201024173028102.png)



*   @ConfigurationProperties只需要写一次即可 ， @Value则需要每个字段都添加
*   松散绑定：这个什么意思呢? 比如我的yml中写的last-name，这个和lastName是一样的， - 后面跟着的字母默认是大写的。这就是松散绑定。可以测试一下
*   JSR303数据校验 ， 这个就是我们可以在字段是增加一层过滤器验证 ， 可以保证数据的合法性
*   复杂类型封装，yml中可以封装对象 ， 使用value就不支持

**结论：**

*   配置yml和配置properties都可以获取到值 ， 强烈推荐 yml；
*   如果我们在某个业务中，只需要获取配置文件中的某个值，可以使用一下 @value；
*   如果说，我们专门编写了一个JavaBean来和配置文件进行一一映射，就直接@configurationProperties，不要犹豫！



# 3 JRS303校验



使用@Validate后，使用@Email的时候会报错，在pom.xml导入如下包

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```



在email加上注解@Email()，括号内的提示内容可以为空，写上更好，这样出错很容易看到是哪里错了！

```java
package com.ly.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@Data
@Component // 注册bean
@ConfigurationProperties(prefix = "student")
@Validated // 数据校验
public class Student {
    private String firstName;
    private String lastName;
    @Email(message="邮箱格式错误") //name必须是邮箱格式
    private String email;
}
```



在application.yaml文件中注入的student信息如下

```yaml
student:
  first-name: yang
  last-name: li
  email: 12306
```



测试程序（关注student即可，其它为前面的测试代码）

```java
package com.ly;

import com.ly.pojo.Dog;
import com.ly.pojo.Person;
import com.ly.pojo.Student;
import com.ly.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * liyang 2020-10-24
 * 测试springboot的两种注入方式
 */
@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
    private Dog dog;

    @Autowired
    private Person person;

    @Autowired
    private User user;

    @Autowired
    private Student stu;

    @Test
    void contextLoads() {
        System.out.println(dog);
        System.out.println(person);
        System.out.println(user);
        System.out.println(stu);
    }

}
```



测试结果：因为邮箱在注入的时候没有按照邮箱格式，@Email()校验这个字段，并提示我们哪里出错！

<img src="SpringBoot.assets/image-20201025230347913.png" alt="image-20201025230347913" style="zoom:50%;" />





**使用数据校验，可以保证数据的正确性** 

常见参数

```java

@NotNull(message="名字不能为空")
private String userName;
@Max(value=120,message="年龄最大不能查过120")
private int age;
@Email(message="邮箱格式错误")
private String email;

空检查
@Null       验证对象是否为null
@NotNull    验证对象是否不为null, 无法查检长度为0的字符串
@NotBlank   检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
@NotEmpty   检查约束元素是否为NULL或者是EMPTY.
    
Booelan检查
@AssertTrue     验证 Boolean 对象是否为 true  
@AssertFalse    验证 Boolean 对象是否为 false  
    
长度检查
@Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内  
@Length(min=, max=) string is between min and max included.

日期检查
@Past       验证 Date 和 Calendar 对象是否在当前时间之前  
@Future     验证 Date 和 Calendar 对象是否在当前时间之后  
@Pattern    验证 String 对象是否符合正则表达式的规则

.......等等
除此以外，我们还可以自定义一些数据校验规则
```

[JSR-303](https://www.jianshu.com/p/554533f88370)



<img src="SpringBoot.assets/image-20201025232336040.png" alt="image-20201025232336040" style="zoom:50%;" />