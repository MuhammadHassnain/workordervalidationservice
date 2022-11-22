package com.goddev.workorder.validationservice.service.impl;

import com.goddev.workorder.validationservice.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DummyDepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;


    @Test
    public void notAValidDepartment(){
        String departmentName  = "This is departmentName Departmetn";

        assertFalse(departmentService.getDepartments().contains(departmentName));
    }
    @Test
    public void mustBeValidDepartment(){
        String departmentName  = "GOoD analysis department";

        assertTrue(departmentService.getDepartments().contains(departmentName));

    }
}