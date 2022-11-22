package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import com.goddev.workorder.validationservice.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentValidatorTest {


    private WorkOrder workOrder;

    @Autowired
    private CurrencyValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }


    @Test
    public void departmentCantBeEmpty(){
        assertThrows(InvalidParameterException.class, ()->underTest.apply(workOrder), "Invalid Department");
    }

    @Test
    public void mustBeValidDepartment(){
        workOrder.setDepartment("GOoD analysis department");
        assertThrows(InvalidParameterException.class,  ()->underTest.apply(workOrder), "In Valid Department");
    }


    @Test
    public void departmentIsInvalid(){
        workOrder.setDepartment("Not a valid Department");

        assertThrows(InvalidParameterException.class,  ()->underTest.apply(workOrder), "In Valid Department");
    }


}