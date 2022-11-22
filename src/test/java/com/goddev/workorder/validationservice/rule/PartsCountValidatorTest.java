package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.Part;
import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class PartsCountValidatorTest {




    private WorkOrder workOrder;

    @Autowired
    private PartsValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }


    @Test
    public void partsCantBeNull(){

        workOrder.setParts(null);

        assertThrows(InvalidParameterException.class, ()->underTest.apply(workOrder));

    }

    @Test
    public void partsCountCantBeZero(){

        workOrder.setParts(new ArrayList<>());
        assertThrows(InvalidParameterException.class, ()->underTest.apply(workOrder));

    }


    @Test
    public void partsCountIsGreaterThenZero(){
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));

        assertDoesNotThrow(()-> underTest.apply(workOrder));
    }
}