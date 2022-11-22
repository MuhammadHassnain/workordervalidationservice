package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FactoryOrderNumberValidatorTest {



    private WorkOrder workOrder;

    @Autowired
    private FactoryOrderNumberValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }


    @Test
    public void factoryOrderNumberCantBeNull(){
        workOrder.setFactoryOrderNumber(null);

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }

    @Test
    public void factoryOrderNumberDoesNotContainOnlySpaces(){
        workOrder.setFactoryOrderNumber("    ");

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }

    @Test
    public  void factoryOrderNumberFirst2LettersAreAlphabetsAndRestAreNumeric(){
        workOrder.setFactoryOrderNumber("BE12332112");

        assertDoesNotThrow(()->underTest.apply(workOrder));
    }

    @Test
    public void factoryOrderNumberOfSizeTenDoesNotMatchesPatternIsInvalid(){
        workOrder.setFactoryOrderNumber("be1234kkkk");
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }

    @Test
    public void aValidFactoryOrderNumberFirstTwoCanBeInLowerCase(){
        workOrder.setFactoryOrderNumber("be12332112");
        assertDoesNotThrow(()->underTest.apply(workOrder));
    }


    @Test
    public void aValidFactoryOrderNumberFirstTwoCanBeInAnyCase(){
        workOrder.setFactoryOrderNumber("Be12332112");
        assertDoesNotThrow(()->underTest.apply(workOrder));
    }



}