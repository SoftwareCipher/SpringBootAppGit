package com.springboot.app.service;

import com.springboot.app.entities.Department;
import com.springboot.app.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DepartmentServiceImplTest {

    Department department;

    @MockBean
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setUp() {
        department = new Department();
        department.setId(1L);
        department.setDesc("#1");
    }

    @Test
    void getAllDepartments() {
        List<Department> allDepartments = new ArrayList<>();
        when(this.departmentRepository.findAll()).thenReturn(allDepartments);

        List<Department> actualAllDepartments = this.departmentRepository.findAll();
        assertSame(allDepartments, actualAllDepartments);
        assertTrue(actualAllDepartments.isEmpty());
        verify(this.departmentRepository).findAll();
    }

    @Test
    void saveOrUpdateDepartment() {
        when(this.departmentRepository.save((Department) any())).thenReturn(department);

        Department department1 = new Department();
        department1.setDesc("The characteristics of someone or something");
        department1.setId(123L);
        this.departmentService.saveOrUpdateDepartment(department1);
        verify(this.departmentRepository).save((Department) any());
        assertEquals("The characteristics of someone or something", department1.getDesc());
        assertEquals(123L, department1.getId());
        assertTrue(this.departmentService.getAllDepartments().isEmpty());
    }

    @Test
    void testGetDepartment() {
        Optional<Department> ofResult = Optional.of(department);
        when(this.departmentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(department, this.departmentService.getDepartment(1L));
        verify(this.departmentRepository).findById((Long) any());
    }

    @Test
    void testDeleteDepartment() {
        doNothing().when(this.departmentRepository).deleteById((Long) any());
        this.departmentService.deleteDepartment(1L);
        verify(this.departmentRepository).deleteById((Long) any());
        assertTrue(this.departmentService.getAllDepartments().isEmpty());
    }
}