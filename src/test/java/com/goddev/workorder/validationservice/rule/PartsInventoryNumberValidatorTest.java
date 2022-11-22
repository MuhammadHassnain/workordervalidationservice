package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.Part;
import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PartsInventoryNumberValidatorTest {




    private WorkOrder workOrder;

    @Autowired
    private PartsInventoryNumberValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }


    @Test
    public void partsAreMustForInventoryPartValidation(){

        workOrder.setParts(null);

        assertThrows(InvalidParameterException.class, ()->underTest.apply(workOrder));

    }

    @Test
    public void partsNumberCantBeBlankOrOnlyContainsSpaces(){

        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber(" ").build();
        workOrder.setParts(List.of(part,par1));

        assertThrows(InvalidParameterException.class, ()->underTest.apply(workOrder));


    }


    @Test
    public void canNotMissPartNumberForAnySinglePart(){

        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber(null).build();
        workOrder.setParts(List.of(part,par1));

        assertThrows(InvalidParameterException.class, ()->underTest.apply(workOrder));


    }

    @Test
    public void partsNumberAreRequiredForAllPartsUnderWorkOrder(){
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));

        assertDoesNotThrow(()-> underTest.apply(workOrder));
    }
}