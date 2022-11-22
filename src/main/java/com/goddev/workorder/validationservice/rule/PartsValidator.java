package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PartsValidator implements ValidationRule {
    private final Set<WorkOrderType> ruleAllowedFor = Set.of(WorkOrderType.REPAIR);

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {

       if(workOrder == null || workOrder.getParts() == null || workOrder.getParts().size() == 0){
           throw  new InvalidParameterException("Parts are missing");
       }
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return ruleAllowedFor.contains(workOrderType);
    }
}
