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
class AnalysisDateValidatorTest {

    private WorkOrder workOrder;

    @Autowired
    private AnalysisDateValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .startDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime())
                .endDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime())
                .analysisDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 20).getTime())

                .build();
    }

    @Test
    public void workOrderMustHaveValidAnalysisDate(){

        assertDoesNotThrow( ()-> underTest.apply(this.workOrder),"Valid WorkOrder So it doesn't Throw Exception"); ;
    }


    @Test
    public void analysisIsNotPossibleWithoutStartDate(){
        //when
        this.workOrder.setAnalysisDate(null);

        //then
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(this.workOrder),"Start Date is missing for Analysis"); ;
    }


    @Test
    public void analysisIsNotPossibleWithoutEndDate(){
        //when
        this.workOrder.setEndDate(null);

        //then
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(this.workOrder),"End Date is missing for Analysis"); ;
    }


    @Test
    public void analysisIsDateShouldNotBeEmpty(){
        //when
        this.workOrder.setAnalysisDate(null);

        //then
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(this.workOrder),"Invalid Analysis Date"); ;
    }

    @Test
    public void analysisDateShouldBeAfterStartDate(){
        //when
        this.workOrder.setAnalysisDate(this.workOrder.getStartDate());

        //then
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(this.workOrder),"Invalid Analysis Date"); ;
    }


    @Test
    public void analysisDateShouldBeBeforeEndDate(){
        //when
        this.workOrder.setAnalysisDate(this.workOrder.getEndDate());

        //then
        assertThrows(InvalidParameterException.class, ()-> underTest.apply(this.workOrder),"Invalid Analysis Date"); ;
    }




}