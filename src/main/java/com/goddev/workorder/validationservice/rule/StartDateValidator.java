package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StartDateValidator implements ValidationRule {


    private final Date currentDate;
    public StartDateValidator() {
        currentDate = new Date();
    }

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {

        if(workOrder == null || workOrder.getStartDate() == null || !workOrder.getStartDate().before(currentDate)){
            throw  new InvalidParameterException("Start Date is invalid");
        }
        
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return true;
    }
}
