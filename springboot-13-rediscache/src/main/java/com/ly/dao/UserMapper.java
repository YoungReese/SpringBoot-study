package com.ly.dao;

import com.ly.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from users")
    List<User> queryUsers();


    int updateUser(User user);

    @Delete("delete from users")
    int deleteUser(int id);

}
