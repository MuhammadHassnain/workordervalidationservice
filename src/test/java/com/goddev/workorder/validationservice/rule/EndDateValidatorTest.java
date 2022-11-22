package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EndDateValidatorTest {



    private WorkOrder workOrder;

    @Autowired
    private EndDateValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }


    @Test
    public void endDateCantBeEmpty(){
        workOrder.setEndDate(null);
        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }

    @Test
    public void startDateIsRequiredForEndDate(){
        workOrder.setStartDate(null);
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }

    @Test
    public void endDateMustBeGreaterThenStartDate(){

        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }

    @Test
    public void endDateMustBeAfterStartDate(){
        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 20).getTime());

        assertDoesNotThrow(()->underTest.apply(workOrder));
    }


}