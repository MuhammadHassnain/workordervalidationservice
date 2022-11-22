package com.goddev.workorder.validationservice.exception;


import com.goddev.workorder.validationservice.dto.ErrorResponse;
import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderHistory;
import com.goddev.workorder.validationservice.dto.WorkerOrderValidationResponse;
import com.goddev.workorder.validationservice.service.WorkOrderHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    private final WorkOrderHistoryService workOrderHistoryService;

    public GlobalExceptionHandler(WorkOrderHistoryService workOrderHistoryService) {
        this.workOrderHistoryService = workOrderHistoryService;
    }


    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<WorkerOrderValidationResponse> handleInvalidRequest(InvalidRequestException exception){

        WorkOrder workOrder = exception.getWorkOrder();
        WorkOrderHistory workOrderHistory = new WorkOrderHistory();
        workOrderHistory.setValid(false);
        workOrderHistory.setWorkOrderType(workOrder.getType().name());
        workOrderHistory.setDepartment(workOrder.getDepartment());
        workOrderHistoryService.saveWorkOrderRequestHistory(workOrderHistory);


        WorkerOrderValidationResponse response = WorkerOrderValidationResponse.invalid(exception.getErrors());
        return  ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(HistoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleHistoryNotFoundException(HistoryNotFoundException exception){
        ErrorResponse error  =  new ErrorResponse(exception.getError());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception){
        ErrorResponse error  =  new ErrorResponse("Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
