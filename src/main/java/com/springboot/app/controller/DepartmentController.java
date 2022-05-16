package com.springboot.app.controller;

import com.springboot.app.entities.Department;
import com.springboot.app.service.impl.DepartmentServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "DepartmentController", description = "DepartmentController | Rest questions")
@RestController
@RequestMapping("/departmentApi")
public class DepartmentController {

    DepartmentServiceInterface deleteEntity;

    @Autowired
    public DepartmentController(DepartmentServiceInterface deleteEntity) {
        this.deleteEntity = deleteEntity;
    }

    @GetMapping("/departments")
    @ApiOperation("Get all departments")
    public List<Department> showAllDepartment() {
        return deleteEntity.getAllDepartments();
    }

    @GetMapping("/department/{id}")
    @ApiOperation("Get department by id")
    public Department getDepart(@PathVariable long id){
        Department department =  deleteEntity.getDepartment(id);
        if(department == null){
            System.out.println("There is no Department with id = " + id + " in database");
        }
        return department;
    }

    @PostMapping("/department")
    @ApiOperation("Save number department")
    public Department saveDepartment(@RequestBody Department department){
        deleteEntity.saveOrUpdateDepartment(department);
        return department;
    }

    @DeleteMapping("/department/{id}")
    @ApiOperation("Delete department by id")
    public String deleteDepartment(@PathVariable long id){
        Department department = deleteEntity.getDepartment(id);
        if(department == null){
            System.out.println("There is no Department with id = " + id + " in database");
        }
        deleteEntity.deleteDepartment(id);
        return "Department with id = " + id + " was delete";
    }
}
