package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FactoryNameValidator implements ValidationRule {

    private final Set<WorkOrderType> ruleAllowedFor = Set.of(WorkOrderType.REPLACEMENT);


    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {
        if(workOrder == null || workOrder.getFactoryName() == null || workOrder.getFactoryName().isBlank()){
            throw  new InvalidParameterException("Factory Name is Missing");
        }
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return ruleAllowedFor.contains(workOrderType);
    }
}
