package com.ly.dao;

import com.ly.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    public User getUserByName(@Param("name") String name);

}
