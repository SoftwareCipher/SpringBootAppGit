package com.springboot.app.service.impl;

import com.springboot.app.entities.Department;

import java.util.List;

public interface DepartmentServiceInterface {
    List<Department> getAllDepartments();

    void saveOrUpdateDepartment(Department dep);

    Department getDepartment(long id);

    void deleteDepartment(long id);
}
