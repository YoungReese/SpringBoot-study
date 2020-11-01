package com.ly.mapper;

import com.ly.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper // 这个注解表示这是一个mybatis的mapper类，可以在程序主入口使用@MapperScan("com.ly.mapper")替代
@Repository
public interface UserMapper {

    List<User> queryUsers();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

}
