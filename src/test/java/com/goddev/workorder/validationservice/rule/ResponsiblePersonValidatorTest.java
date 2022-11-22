package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ResponsiblePersonValidatorTest {




    private WorkOrder workOrder;

    @Autowired
    private ResponsiblePersonValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }


    @Test
    public void responsiblePersonCantBeNull(){
        workOrder.setResponsiblePerson(null);

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }

    @Test
    public void responsiblePersonDoesNotContainOnlySpaces(){
        workOrder.setResponsiblePerson("    ");

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }

    @Test
    public  void responsiblePersonContainsValueIsValid(){
        workOrder.setResponsiblePerson("Hassnain");

        assertDoesNotThrow(()->underTest.apply(workOrder));
    }

}