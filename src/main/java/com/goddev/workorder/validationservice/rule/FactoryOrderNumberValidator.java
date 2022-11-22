package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.regex.Pattern;

@Service
public class FactoryOrderNumberValidator implements ValidationRule {

    private final Set<WorkOrderType> ruleAllowedFor = Set.of(WorkOrderType.REPLACEMENT);
    private final String PATTERN = "^[a-zA-Z]{2}\\d{8}$";

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {
        if(workOrder == null
                || workOrder.getFactoryOrderNumber() == null
                || workOrder.getFactoryOrderNumber().isBlank()
                || !Pattern.matches(this.PATTERN, workOrder.getFactoryOrderNumber())){
            throw  new InvalidParameterException("Factory Order is Invalid");
        }
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {

        return ruleAllowedFor.contains(workOrderType);

    }
}
