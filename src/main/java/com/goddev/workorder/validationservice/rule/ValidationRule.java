package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;

public interface ValidationRule {
    void apply(WorkOrder workOrder) throws InvalidParameterException;
    boolean ruleFor(WorkOrderType workOrderType);
}
