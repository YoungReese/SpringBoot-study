package com.ly.dao;

import com.ly.pojo.Department;
import com.ly.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * liyang 2020-10-27
 * 员工的dao
 */
@Repository
public class EmployeeDao {

    // 模拟数据库中的数据，最好直接整合数据库
    private static Map<Integer, Employee> employees = null;

    // 员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>(); // 创建了一个部门表
        employees.put(1001, new Employee(1001, "A", "A12306@mail.com", 1, new Department(101, "教学部"), new Date()));
        employees.put(1002, new Employee(1002, "B", "B12306@mail.com", 1, new Department(101, "市场部"), new Date()));
        employees.put(1003, new Employee(1003, "C", "C12306@mail.com", 1, new Department(101, "教研部"), new Date()));
        employees.put(1004, new Employee(1004, "D", "D12306@mail.com", 1, new Department(101, "运营部"), new Date()));
        employees.put(1005, new Employee(1005, "E", "E12306@mail.com", 1, new Department(101, "后勤部"), new Date()));
    }


    // 模拟数据库的增删改查
    private static Integer initId = 1005;

    // 增加一个员工
    public void add(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(++initId); // 模拟组件自增
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }


    // 查询所有员工
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    // 根据id查询员工
    public Employee getEmployeeById(int id) {
        return employees.get(id);
    }

    // 根据id删除员工
    public void removeEmployeeById(int id) {
        employees.remove(id);
    }

}
