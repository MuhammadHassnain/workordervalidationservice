package com.goddev.workorder.validationservice.service.impl;

import com.goddev.workorder.validationservice.service.CurrencyService;
import com.goddev.workorder.validationservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ISOCurrencyService implements CurrencyService {

    @Override
    public Set<String> getValidCurrencies() {
        return Set.of("USD", "PKR");
    }
}
