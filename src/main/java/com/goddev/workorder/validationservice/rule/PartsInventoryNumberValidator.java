package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.Part;
import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PartsInventoryNumberValidator implements ValidationRule {
    private final Set<WorkOrderType> ruleAllowedFor = Set.of(WorkOrderType.REPLACEMENT);
    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {
        if(workOrder == null || workOrder.getParts() == null){
            throw  new InvalidParameterException("Parts are Missing for Replacement");
        }

        for (Part part:
             workOrder.getParts()) {

            if(part.getInventoryNumber() == null || part.getInventoryNumber().isBlank()){
                throw  new InvalidParameterException("All Factory Parts must have inventory Number");
            }
        }
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return ruleAllowedFor.contains(workOrderType);
    }
}
