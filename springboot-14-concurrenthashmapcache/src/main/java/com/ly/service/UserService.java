package com.ly.service;

import com.ly.pojo.User;

import java.util.List;

public interface UserService {


    List<User> queryAll();

    User queryLatest();

}
