package com.goddev.workorder.validationservice.service;


import com.goddev.workorder.validationservice.dto.WorkOrderHistory;
import com.goddev.workorder.validationservice.exception.HistoryNotFoundException;
import com.goddev.workorder.validationservice.repository.WorkOrderHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkOrderHistoryService {


    private final WorkOrderHistoryRepository workOrderHistoryRepository;

    public WorkOrderHistoryService(WorkOrderHistoryRepository workOrderHistoryRepository) {
        this.workOrderHistoryRepository = workOrderHistoryRepository;
    }


    public WorkOrderHistory saveWorkOrderRequestHistory(WorkOrderHistory workOrderHistory){
        return workOrderHistoryRepository.save(workOrderHistory);
    }

    public WorkOrderHistory findWorkOrderHistoryById(Integer id){
        return workOrderHistoryRepository.findById(id).orElseThrow(()->new HistoryNotFoundException(id));
    }

    public List<WorkOrderHistory> findAllHistory(){
        return workOrderHistoryRepository.findAll();
    }
}
