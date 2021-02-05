package com.ly.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.dao.StudentMapper;
import com.ly.pojo.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
