package com.goddev.workorder.validationservice.repository;

import com.goddev.workorder.validationservice.dto.WorkOrderHistory;

import java.util.List;
import java.util.Optional;

public interface WorkOrderHistoryRepository  {


    Optional<WorkOrderHistory> findById(Integer id);
    WorkOrderHistory save(WorkOrderHistory workOrderHistory);
    List<WorkOrderHistory> findAll();

}
