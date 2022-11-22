package com.goddev.workorder.validationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkerOrderValidationResponse {

    private String status;
    private List<String> errors;

    private WorkerOrderValidationResponse(){}

    public static WorkerOrderValidationResponse invalid(List<String> errors){
        WorkerOrderValidationResponse workerOrderValidationResponse = new WorkerOrderValidationResponse();
        workerOrderValidationResponse.status = "Invalid";
        workerOrderValidationResponse.errors = errors;
        return workerOrderValidationResponse;
    }

    public static  WorkerOrderValidationResponse valid(){
        WorkerOrderValidationResponse workerOrderValidationResponse = new WorkerOrderValidationResponse();
        workerOrderValidationResponse.status = "valid";
        workerOrderValidationResponse.errors = null;
        return workerOrderValidationResponse;
    }
}
