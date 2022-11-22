package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class AnalysisDateValidator implements ValidationRule {

    private final Set<WorkOrderType> ruleAllowedFor = Set.of(WorkOrderType.REPAIR);

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {

        if(workOrder == null || workOrder.getStartDate() == null){
            throw new InvalidParameterException("Start Date is missing for analysis");
        }

        if(workOrder.getEndDate() == null){
            throw new InvalidParameterException("End Date is missing for analysis");
        }

        if(workOrder.getAnalysisDate() == null
                || !(workOrder.getAnalysisDate().after(workOrder.getStartDate())
                    && workOrder.getAnalysisDate().before(workOrder.getEndDate()))
        ){
            throw new InvalidParameterException("Analysis Date is Invalid");
        }
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return ruleAllowedFor.contains(workOrderType);
    }
}
