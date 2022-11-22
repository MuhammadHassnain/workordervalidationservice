package com.goddev.workorder.validationservice.controller;


import com.goddev.workorder.validationservice.dto.WorkOrderHistory;
import com.goddev.workorder.validationservice.service.WorkOrderHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/work-order/history")
public class WorkOrderHistoryController {

    private final WorkOrderHistoryService workOrderHistoryService;

    public WorkOrderHistoryController(WorkOrderHistoryService workOrderHistoryService) {
        this.workOrderHistoryService = workOrderHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<WorkOrderHistory>> findAll(){
        return ResponseEntity.ok(workOrderHistoryService.findAllHistory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOrderHistory> findWorkOrderHistory(@PathVariable("id") Integer id ){
        return ResponseEntity.ok(workOrderHistoryService.findWorkOrderHistoryById(id));
    }

}
