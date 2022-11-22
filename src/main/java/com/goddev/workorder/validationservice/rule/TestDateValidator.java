package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TestDateValidator implements ValidationRule {

    private final Set<WorkOrderType> ruleAllowedFor = Set.of(WorkOrderType.REPAIR);



    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {


        if(workOrder.getEndDate() == null){
            throw new InvalidParameterException("End Date is missing for test");
        }

        if(workOrder.getAnalysisDate() == null ){
            throw new InvalidParameterException("Analysis date is missing for test date");
        }

        if(workOrder.getTestDate() == null
                || !(workOrder.getTestDate().after(workOrder.getAnalysisDate())
                && workOrder.getAnalysisDate().before(workOrder.getEndDate()))
        ){
            throw new InvalidParameterException("Test Date is invalid");
        }
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return ruleAllowedFor.contains(workOrderType);
    }
}
