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
class CurrencyValidatorTest {


    private WorkOrder workOrder;

    @Autowired
    private CurrencyValidator underTest;

    @BeforeEach
    public void setWorkOrder(){
        workOrder =  WorkOrder.builder()
                .currency("USD")
                .build();
    }


    @Test
    public void USDIsAValidISOCurrency(){
        assertDoesNotThrow(()->underTest.apply(workOrder) , "USD is Valid Currency");
    }


    @Test
    public void PKRIsAValidISOCurrency(){

        workOrder.setCurrency("PKR");
        assertDoesNotThrow(()->underTest.apply(workOrder) , "PKR is Valid Currency");
    }


    @Test
    public void ABCIsNOTAValidISOCurrency(){
        workOrder.setCurrency("ABC");
        assertThrows(InvalidParameterException.class, ()->underTest.apply(workOrder) , "Currency is Invalid");
    }


    @Test
    public void currencyCantBeEmpty(){
        workOrder.setCurrency(null);
        assertThrows(InvalidParameterException.class, ()->underTest.apply(workOrder) , "Currency is Invalid");
    }

}