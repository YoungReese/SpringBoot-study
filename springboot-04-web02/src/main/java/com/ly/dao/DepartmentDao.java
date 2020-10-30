package com.ly.dao;

import com.ly.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * liyang 2020-10-27
 * 部门dao
 */
@Repository // 被Spring托管
public class DepartmentDao {

    // 模拟数据库中的数据，最好直接整合数据库
    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<>(); // 创建了一个部门表
        departments.put(101, new Department(101, "教学部"));
        departments.put(102, new Department(102, "市场部"));
        departments.put(103, new Department(103, "教研部"));
        departments.put(104, new Department(104, "运营部"));
        departments.put(105, new Department(105, "后勤部"));
    }


    // 模拟数据库的增删改查
    // 查询所有部门
    public Collection<Department> getDepartments() {
        return departments.values();
    }
    // 根据id查询部门
    public Department getDepartmentById(int id) {
        return departments.get(id);
    }

}
