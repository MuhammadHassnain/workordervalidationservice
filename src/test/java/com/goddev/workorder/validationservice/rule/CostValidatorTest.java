package com.goddev.workorder.validationservice.rule;


import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CostValidatorTest {




    private WorkOrder workOrder;

    @Autowired
    private CostValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .cost(new BigDecimal("10.5"))
                .build();
    }


    @Test
    public void validWorkOrderCostIsGreaterThenZero(){


        assertDoesNotThrow(()->underTest.apply(this.workOrder),"Valid Work Order");
    }

    @Test
    public void workOrderCostCantBeZero(){
        workOrder.setCost(new BigDecimal("0"));

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(this.workOrder),"Invalid WorkOrder Cost"); ;



    }


    @Test
    public void workOrderCostCantBeNegative(){
        workOrder.setCost(new BigDecimal("-100"));

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(this.workOrder),"Invalid WorkOrder Cost"); ;

    }

    @Test
    public void costOfWorkerCantBeEmpty(){
        //when
        workOrder.setCost(null);

        //then
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(this.workOrder),"Invalid WorkOrder Cost"); ;

    }
}