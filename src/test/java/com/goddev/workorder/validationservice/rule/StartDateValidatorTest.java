package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StartDateValidatorTest {



    private WorkOrder workOrder;

    @Autowired
    private StartDateValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }


    @Test
    public void startDateCantBeNull(){
        workOrder.setStartDate(null);

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }
    @Test
    public void startDateCantBeToday(){
        workOrder.setStartDate(new Date());
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));

    }
    @Test
    public void startDateCantBeAfterCurrentDate(){
        workOrder.setStartDate(new GregorianCalendar(2023, Calendar.NOVEMBER, 18).getTime());
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }

    @Test
    public void startDateMustBeBeforeCurrentDate(){
        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 19).getTime());
        assertDoesNotThrow(()->underTest.apply(workOrder));
    }
}
