package com.goddev.workorder.validationservice.service;

import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.rule.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RuleProviderTest {




    private final RuleProvider ruleProvider;
    private final List<ValidationRule> validationRuleList;
    private final CurrencyService currencyService;
    private final DepartmentService departmentService;
    private final StartDateValidator startDateValidator;
    private final AnalysisDateValidator analysisDateValidator;
    private final CostValidator costValidator;
    private final CurrencyValidator currencyValidator;
    private final DepartmentValidator departmentValidator;
    private final EndDateValidator endDateValidator;
    private final FactoryNameValidator factoryNameValidator;
    private final FactoryOrderNumberValidator factoryOrderNumberValidator;
    private final PartsInventoryNumberValidator partsInventoryNumberValidator;
    private final PartsValidator partsValidator;
    private final ResponsiblePersonValidator responsiblePersonValidator;
    private final TestDateValidator testDateValidator;

    @Autowired
    RuleProviderTest(RuleProvider ruleProvider, List<ValidationRule> validationRuleList, StartDateValidator startDateValidator, CurrencyService currencyService, DepartmentService departmentService, AnalysisDateValidator analysisDateValidator, CostValidator costValidator, CurrencyValidator currencyValidator, DepartmentValidator departmentValidator, EndDateValidator endDateValidator, FactoryNameValidator factoryNameValidator, FactoryOrderNumberValidator factoryOrderNumberValidator, PartsInventoryNumberValidator partsInventoryNumberValidator, PartsValidator partsValidator, ResponsiblePersonValidator responsiblePersonValidator, TestDateValidator testDateValidator) {
        this.ruleProvider = ruleProvider;
        this.validationRuleList = validationRuleList;
        this.startDateValidator = startDateValidator;
        this.currencyService = currencyService;
        this.departmentService = departmentService;
        this.analysisDateValidator = analysisDateValidator;
        this.costValidator = costValidator;
        this.currencyValidator = currencyValidator;
        this.departmentValidator = departmentValidator;
        this.endDateValidator = endDateValidator;
        this.factoryNameValidator = factoryNameValidator;
        this.factoryOrderNumberValidator = factoryOrderNumberValidator;
        this.partsInventoryNumberValidator = partsInventoryNumberValidator;
        this.partsValidator = partsValidator;
        this.responsiblePersonValidator = responsiblePersonValidator;
        this.testDateValidator = testDateValidator;
    }


    @Test
    public void analysisWorkOrderOnlyContainsGeneralRules(){


        Set<ValidationRule> analysisRules = this.validationRuleList.stream().filter(validationRule -> validationRule.ruleFor(WorkOrderType.ANALYSIS)).collect(Collectors.toSet());


        assertTrue(analysisRules.contains(startDateValidator));
        assertTrue(analysisRules.contains(endDateValidator));
        assertTrue(analysisRules.contains(currencyValidator));
        assertTrue(analysisRules.contains(costValidator));
        assertTrue(analysisRules.contains(departmentValidator));


        assertFalse(analysisRules.contains(factoryNameValidator));
        assertFalse(analysisRules.contains(factoryOrderNumberValidator));
        assertFalse(analysisRules.contains(partsInventoryNumberValidator));

        assertFalse(analysisRules.contains(testDateValidator));
        assertFalse(analysisRules.contains(analysisDateValidator));
        assertFalse(analysisRules.contains(partsValidator));
        assertFalse(analysisRules.contains(responsiblePersonValidator));



    }


    @Test
    public void repairWorkOrderContainsGeneralAndRepairRules(){


        Set<ValidationRule> analysisRules = this.validationRuleList.stream().filter(validationRule -> validationRule.ruleFor(WorkOrderType.REPAIR)).collect(Collectors.toSet());


        assertTrue(analysisRules.contains(startDateValidator));
        assertTrue(analysisRules.contains(endDateValidator));
        assertTrue(analysisRules.contains(currencyValidator));
        assertTrue(analysisRules.contains(costValidator));
        assertTrue(analysisRules.contains(departmentValidator));


        assertFalse(analysisRules.contains(factoryNameValidator));
        assertFalse(analysisRules.contains(factoryOrderNumberValidator));
        assertFalse(analysisRules.contains(partsInventoryNumberValidator));

        assertTrue(analysisRules.contains(testDateValidator));
        assertTrue(analysisRules.contains(analysisDateValidator));
        assertTrue(analysisRules.contains(partsValidator));
        assertTrue(analysisRules.contains(responsiblePersonValidator));



    }
    @Test
    public void replacementWorkOrderOnlyContainsGeneralAndReplacementRules(){


        Set<ValidationRule> analysisRules = this.validationRuleList
                .stream()
                .filter(validationRule -> validationRule.ruleFor(WorkOrderType.REPLACEMENT))
                .collect(Collectors.toSet());


        assertTrue(analysisRules.contains(startDateValidator));
        assertTrue(analysisRules.contains(endDateValidator));
        assertTrue(analysisRules.contains(currencyValidator));
        assertTrue(analysisRules.contains(costValidator));
        assertTrue(analysisRules.contains(departmentValidator));


        assertTrue(analysisRules.contains(factoryNameValidator));
        assertTrue(analysisRules.contains(factoryOrderNumberValidator));
        assertTrue(analysisRules.contains(partsInventoryNumberValidator));

        assertFalse(analysisRules.contains(testDateValidator));
        assertFalse(analysisRules.contains(analysisDateValidator));
        assertFalse(analysisRules.contains(partsValidator));
        assertFalse(analysisRules.contains(responsiblePersonValidator));



    }


}