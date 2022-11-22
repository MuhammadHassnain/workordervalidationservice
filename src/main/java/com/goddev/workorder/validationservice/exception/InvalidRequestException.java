package com.goddev.workorder.validationservice.exception;

import com.goddev.workorder.validationservice.dto.WorkOrder;

import java.util.List;

public class InvalidRequestException extends RuntimeException{


    private List<String> errors;
    private WorkOrder workOrder;

    public InvalidRequestException(List<String> errors) {
        this.errors = errors;
    }

    public InvalidRequestException(List<String> errors, WorkOrder workOrder) {
        this.errors = errors;
        this.workOrder = workOrder;
    }

    public List<String> getErrors(){
        return this.errors;
    }

    public WorkOrder getWorkOrder(){
        return  workOrder;
    }
}
