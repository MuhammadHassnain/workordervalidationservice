package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CostValidator implements ValidationRule {


    public CostValidator() {

    }

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {

        if((workOrder == null) || (workOrder.getCost() == null) || (workOrder.getCost().compareTo(new BigDecimal(0)) != 1)){
            throw  new InvalidParameterException("Cost is Invalid");
        }
        
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return true;
    }
}
