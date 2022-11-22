package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

@Service
public class EndDateValidator implements ValidationRule {

    public EndDateValidator() {

    }

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {

        if(workOrder == null || workOrder.getStartDate() == null){
            throw new InvalidParameterException("Start Date is missing for end date validation");
        }
        if(workOrder.getEndDate() == null || !workOrder.getEndDate().after(workOrder.getStartDate())){
            throw  new InvalidParameterException("End Date is Invalid");
        }
        
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return true;
    }
}
