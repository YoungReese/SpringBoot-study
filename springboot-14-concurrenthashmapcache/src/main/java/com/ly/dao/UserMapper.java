package com.ly.dao;

import com.ly.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from users")
    List<User> queryAll();

    @Select("select * from users order by id DESC limit 1")
    User queryLatest();

}
