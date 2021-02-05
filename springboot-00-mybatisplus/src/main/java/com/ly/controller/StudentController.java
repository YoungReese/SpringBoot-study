package com.ly.controller;

import com.ly.dao.StudentMapper;
import com.ly.pojo.Student;
import com.ly.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentController {

    /**
     * 测试使用service完成查询，验证service可以正常工作
     */
    @Autowired
    private StudentService studentService;

    @PostMapping("/getList")
    public List<Student> getList() {
        System.out.println("/getList");
        List<Student> res = studentService.list();
        return res;
    }

    /**
     * 测试用dao完成查询，验证dao可以正常工作
     */
    @Autowired
    private StudentMapper studentMapper;

    @PostMapping("/getAll")
    public List<Student> getAll() {
        System.out.println("/getAll");
        List<Student> res = studentMapper.selectList(null);
        return res;
    }

}
