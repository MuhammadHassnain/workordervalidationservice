package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FactoryNameValidatorTest {



    private WorkOrder workOrder;

    @Autowired
    private FactoryNameValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }


    @Test
    public void factoryNameCantBeNull(){
        workOrder.setFactoryName(null);

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }

    @Test
    public void factoryNameDoesNotContainOnlySpaces(){
        workOrder.setFactoryName("    ");

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }

    @Test
    public  void factoryNameContainsValueIsValid(){
        workOrder.setFactoryName("Sample Factory name");

        assertDoesNotThrow(()->underTest.apply(workOrder));
    }




}