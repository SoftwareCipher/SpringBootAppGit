package com.springboot.app.service;

import com.springboot.app.exception_handling.NoSuchEntityException;
import com.springboot.app.repository.DepartmentRepository;
import com.springboot.app.entities.Department;
import com.springboot.app.service.impl.DepartmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentServiceInterface {

    DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void saveOrUpdateDepartment(Department dep) {
        departmentRepository.save(dep);
    }

    @Override
    public Department getDepartment(long id) {
        Department department = null;
        Optional<Department> optional = departmentRepository.findById(id);
        if(optional.isPresent()){
            department = optional.get();
        }else{
            throw new NoSuchEntityException("There is no Department with id = "
                    + id + " in database");
        }
        return department;
    }

    @Override
    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }
}
