package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ResponsiblePersonValidator implements ValidationRule {
    private final Set<WorkOrderType> ruleAllowedFor = Set.of(WorkOrderType.REPAIR);

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {
        if(workOrder == null || workOrder.getResponsiblePerson() == null || workOrder.getResponsiblePerson().isBlank()){
            throw new InvalidParameterException("Responsible Person is Missing");
        }
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return ruleAllowedFor.contains(workOrderType);
    }
}
