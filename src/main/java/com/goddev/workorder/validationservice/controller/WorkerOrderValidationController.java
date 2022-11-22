package com.goddev.workorder.validationservice.controller;


import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderHistory;
import com.goddev.workorder.validationservice.dto.WorkerOrderValidationResponse;
import com.goddev.workorder.validationservice.service.WorkOrderHistoryService;
import com.goddev.workorder.validationservice.service.WorkOrderValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/work-order")
public class WorkerOrderValidationController {


    private final WorkOrderValidationService workOrderValidationService;
    private final WorkOrderHistoryService workOrderHistoryService;

    public WorkerOrderValidationController(WorkOrderValidationService workOrderValidationService, WorkOrderHistoryService workOrderHistoryService) {
        this.workOrderValidationService = workOrderValidationService;
        this.workOrderHistoryService = workOrderHistoryService;
    }


    @PostMapping("/validate")
    public ResponseEntity<WorkerOrderValidationResponse> validate(@RequestBody WorkOrder workOrder)  {
        workOrderValidationService.validate(workOrder);
        WorkOrderHistory workOrderHistory = new WorkOrderHistory();

        workOrderHistory.setValid(true);
        workOrderHistory.setWorkOrderType(workOrder.getType().name());
        workOrderHistory.setDepartment(workOrder.getDepartment());
        workOrderHistoryService.saveWorkOrderRequestHistory(workOrderHistory);

        return ResponseEntity.ok(WorkerOrderValidationResponse.valid());
    }


}
