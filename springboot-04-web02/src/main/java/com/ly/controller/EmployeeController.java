package com.ly.controller;

import com.ly.dao.DepartmentDao;
import com.ly.dao.EmployeeDao;

import com.ly.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;


    @RequestMapping("/employees")
    public String getEmployees(Model model) {
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("employees", employees);
        return "employee/list";
    }


    @GetMapping("/toAddPage")
    public String toAddPage(Model model) {
        model.addAttribute("departments", departmentDao.getDepartments());
        return "employee/add";
    }

    @PostMapping("/addEmployee")
    public String add(Employee employee) {
        employeeDao.add(employee);
        return "redirect:/employees";
    }

    @GetMapping("/toUpdatePage/{id}")
    public String toUpdatePage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentDao.getDepartments());
        return "employee/update";
    }

    @PostMapping("/updateEmployee")
    public String update(Employee employee) {
        employeeDao.add(employee);
        return "redirect:/employees";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeDao.removeEmployeeById(id);
        return "redirect:/employees";
    }

}
