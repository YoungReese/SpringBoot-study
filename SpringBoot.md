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



 



# 2 配置

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





































```java

```





```java

```

