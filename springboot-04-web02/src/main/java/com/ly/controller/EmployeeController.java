package com.ly.controller;

import com.ly.dao.EmployeeDao;
import com.ly.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/employees")
    public String getEmployees(Model model) {

        Collection<Employee> employees = employeeDao.getEmployees();

        model.addAttribute("employees", employees);

        return "employee/list";
    }

}
