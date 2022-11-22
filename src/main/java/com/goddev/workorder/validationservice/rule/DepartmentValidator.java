package com.goddev.workorder.validationservice.rule;

import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import com.goddev.workorder.validationservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentValidator implements ValidationRule {

    private DepartmentService departmentService;


    public DepartmentValidator(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void apply(WorkOrder workOrder) throws InvalidParameterException {
        if(workOrder == null
                || workOrder.getDepartment() == null
                || workOrder.getDepartment().isBlank()
                || !departmentService.getDepartments().contains(workOrder.getDepartment())){
            throw new InvalidParameterException("Department is Invalid");
        }
        
    }

    @Override
    public boolean ruleFor(WorkOrderType workOrderType) {
        return true;
    }
}
