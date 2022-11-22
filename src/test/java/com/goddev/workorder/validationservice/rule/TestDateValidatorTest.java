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
class TestDateValidatorTest {

    private WorkOrder workOrder;

    @Autowired
    private TestDateValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .build();
    }



    @Test
    public void analysisDateIsRequiredForTestDate(){
        workOrder.setTestDate(null);

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }

    @Test
    public void endDateIsRequiredForTestDate(){
        workOrder.setEndDate(null);

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }

    @Test
    public void testDateCantBeEmpty(){
        workOrder.setTestDate(null);

        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }
    @Test
    public void testDateCantBeBeforeAnalysisDate(){
        workOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setTestDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 17).getTime());
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }

    @Test
    public void testDateCantBeAfterEndDate(){
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 16).getTime());
        workOrder.setTestDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 17).getTime());
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(workOrder));
    }

    @Test
    public void testDateMustBetweenAnalysisAndEndDate(){
        workOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 16).getTime());
        workOrder.setTestDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 17).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        assertDoesNotThrow( ()-> underTest.apply(workOrder));
    }



}