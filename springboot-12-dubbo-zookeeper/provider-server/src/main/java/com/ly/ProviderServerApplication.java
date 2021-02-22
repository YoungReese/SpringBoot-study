package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * liyang 2021-02-22
 * 启动本项目，首先需要启动zookeeper
 * zookeeper启动：zkServer start
 * zookeeper关闭：zkServer stop
 *
 * 如果需要看服务注册信息，需要dubbo-admin，步骤如下
 *
 * 步骤1（有jar包，跳过此步骤）：
 * 没有dubbo-admin客户端的jar包（有jar包，跳过此步骤）
 * 下载地址：https://github.com/apache/dubbo
 *
 * 步骤2（有jar包，跳过此步骤）：
 * 解压后在项目目录下打包dubbo-admin：
 * mvn clean package -Dmaven.test.skip=true
 *
 * 步骤3：
 * 以下命令打开dubbo-admin：当这个服务启动后，可以在注册中心看到服务信息
 * java -jar dubbo-admin-0.0.1-SNAPSHOT.jar
 *
 * 步骤4：
 * 访问地址：http://localhost:7001/
 *
 * 步骤5：
 * 账号：root
 * 密码：root
 */
@SpringBootApplication
public class ProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderServerApplication.class, args);
    }

}
