package com.goddev.workorder.validationservice.service;


import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import com.goddev.workorder.validationservice.exception.InvalidRequestException;
import com.goddev.workorder.validationservice.rule.ValidationRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkOrderValidationService {


    private final RuleProvider ruleProvider;

    public WorkOrderValidationService(RuleProvider ruleProvider) {
        this.ruleProvider = ruleProvider;
    }



    public boolean validate(WorkOrder workOrder) throws InvalidRequestException{

        List<String> errors = new ArrayList<>();

        if(workOrder == null || workOrder.getType() == null ||
                !Arrays.stream(WorkOrderType.values()).toList().contains(workOrder.getType())){
            errors.add("Work Order Type is missing");
            throw new InvalidRequestException(errors, workOrder);
        }
        for (ValidationRule validationRule:
             ruleProvider.getRuleForWorkOrder(workOrder.getType())) {
            try{
                validationRule.apply(workOrder);
            }catch ( InvalidParameterException exception){
                errors.add(exception.getMessage());
            }
        }

        if(errors.isEmpty())
            return true;
        throw new InvalidRequestException(errors,workOrder);
    }

}
