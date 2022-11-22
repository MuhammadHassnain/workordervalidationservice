package com.goddev.workorder.validationservice.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class ISOCurrencyServiceTest {

    @Autowired
    private ISOCurrencyService isoCurrencyService;

    private Set<String> curencies;

    @BeforeEach
    void setUp() {
        curencies = isoCurrencyService.getValidCurrencies();
    }

    @Test
    public void USDIsValidISOCurrency(){
        assertTrue(curencies.contains("USD"));
    }
    @Test
    public void PKRIsValidISOCurrency(){
        assertTrue(curencies.contains("PKR"));
    }
    @Test
    public void XYZIsNotAValidISOCurrency(){
        assertFalse(curencies.contains("XYZ"));
    }
}