package com.goddev.workorder.validationservice.service;

import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.rule.ValidationRule;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RuleProvider {


    private final List<ValidationRule> validationRules;

    private final Map<WorkOrderType, Set<ValidationRule>> validationRuleCache;

    public RuleProvider(List<ValidationRule> validationRules) {
        this.validationRules = validationRules;
        this.validationRuleCache = new HashMap<>();
    }

    public Set<ValidationRule> getRuleForWorkOrder(WorkOrderType workOrderType){

        if(validationRuleCache.containsKey(workOrderType)) return validationRuleCache.get(workOrderType);

        Set<ValidationRule> WOValidationRules = validationRules.stream()
                .filter(validationRule -> validationRule.ruleFor(workOrderType))
                .collect(Collectors.toSet());

        validationRuleCache.put(workOrderType, WOValidationRules);
        return WOValidationRules;
    }
}
