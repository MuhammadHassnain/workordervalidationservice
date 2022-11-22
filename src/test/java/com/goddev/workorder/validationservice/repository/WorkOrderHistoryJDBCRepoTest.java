package com.goddev.workorder.validationservice.repository;

import com.goddev.workorder.validationservice.dto.WorkOrderHistory;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class WorkOrderHistoryJDBCRepoTest {

    @Autowired
    WorkOrderHistoryJDBCRepo workOrderHistoryJDBCRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void noRecordInDatabase(){
        //given

        //when

        //then
        assertEquals(workOrderHistoryJDBCRepo.findById(0), Optional.empty());
    }


    @Test
    void checkIfWorkOrderHistoryHasBeenInserted(){
        //given
        WorkOrderHistory workOrderHistory = new WorkOrderHistory();
        workOrderHistory.setDepartment("department");
        workOrderHistory.setWorkOrderType(WorkOrderType.REPLACEMENT.name());
        workOrderHistory.setValid(true);
        //when

        WorkOrderHistory insertedOrder = workOrderHistoryJDBCRepo.save(workOrderHistory);


        //then



        assertTrue(insertedOrder.getId()!= null && insertedOrder.getId() > 0 );
        assertEquals(insertedOrder.getValid(),true);
        assertEquals(insertedOrder.getDepartment(),"department");
        assertEquals(insertedOrder.getWorkOrderType(), WorkOrderType.REPLACEMENT.name());

    }



    @Test
    void findAllShouldReturn2WorkOrderHistoryRecord(){
        //given
        WorkOrderHistory workOrderHistory1 = new WorkOrderHistory();
        workOrderHistory1.setDepartment("SAMPLE");
        workOrderHistory1.setWorkOrderType(WorkOrderType.REPLACEMENT.name());
        workOrderHistory1.setValid(true);

        WorkOrderHistory workOrderHistory2 = new WorkOrderHistory();
        workOrderHistory2.setDepartment("SAMPLE");
        workOrderHistory2.setWorkOrderType(WorkOrderType.REPLACEMENT.name());
        workOrderHistory2.setValid(true);
        //when

         workOrderHistoryJDBCRepo.save(workOrderHistory2);
         workOrderHistoryJDBCRepo.save(workOrderHistory1);

        //then
        List<WorkOrderHistory> allWorkOrder = workOrderHistoryJDBCRepo.findAll();


        assertEquals(2,allWorkOrder.size());

    }


    @BeforeEach
    void cleanUp(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "WORK_ORDER_VALIDATION_HISTORY");
    }




}