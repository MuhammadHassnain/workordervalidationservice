package com.goddev.workorder.validationservice.service;

import com.goddev.workorder.validationservice.dto.WorkOrderHistory;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.HistoryNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WorkOrderHistoryServiceTest {


    private final WorkOrderHistoryService workOrderHistoryService;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    WorkOrderHistoryServiceTest(WorkOrderHistoryService workOrderHistoryService, JdbcTemplate jdbcTemplate) {
        this.workOrderHistoryService = workOrderHistoryService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    void workOrderHistoryMustBePersistedCorrectly(){
        WorkOrderHistory orderHistory = new WorkOrderHistory();
        orderHistory.setWorkOrderType(WorkOrderType.REPLACEMENT.name());
        orderHistory.setValid(true);
        orderHistory.setDepartment("department");


        WorkOrderHistory workOrderHistory = workOrderHistoryService.saveWorkOrderRequestHistory(orderHistory);


        assertNotNull(workOrderHistory);
        assertNotNull(workOrderHistory.getId());
        assertTrue(workOrderHistory.getId() > 0);
        assertEquals(workOrderHistory.getWorkOrderType(), WorkOrderType.REPLACEMENT.name());
        assertTrue(workOrderHistory.getValid());
        assertEquals(workOrderHistory.getDepartment(), "department");

    }

    @Test
    void workOrderDoesNotExistInDB(){
        assertThrows(HistoryNotFoundException.class, ()-> workOrderHistoryService.findWorkOrderHistoryById(100));
    }

    @BeforeEach
    void cleanUp(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "WORK_ORDER_VALIDATION_HISTORY");
    }



}