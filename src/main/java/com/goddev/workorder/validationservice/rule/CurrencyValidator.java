package com.goddev.workorder.validationservice.rule;


import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import com.goddev.workorder.validationservice.service.CurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyValidator implements ValidationRule {



    private final CurrencyService currencyService;

    public CurrencyValidator(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {
        if(workOrder == null
                || workOrder.getCurrency() == null
                || !this.currencyService.getValidCurrencies().contains(workOrder.getCurrency())){
            throw new InvalidParameterException("Currency Is Invalid");
        }
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return true;
    }
}
