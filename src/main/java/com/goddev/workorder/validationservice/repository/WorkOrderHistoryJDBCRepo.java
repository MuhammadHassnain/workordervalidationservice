package com.goddev.workorder.validationservice.repository;

import com.goddev.workorder.validationservice.dto.WorkOrderHistory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class WorkOrderHistoryJDBCRepo implements WorkOrderHistoryRepository{

    private final JdbcTemplate jdbcTemplate;

    public WorkOrderHistoryJDBCRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<WorkOrderHistory> findById(@NonNull Integer id) {
        List<WorkOrderHistory> result = jdbcTemplate.query("SELECT * FROM WORK_ORDER_VALIDATION_HISTORY WHERE ID = ?"
                , new Object[]{id}
                , new int[]{Types.INTEGER}
                , new  WorkOrderHistory.WorkOrderHistoryRowMapper()
        );
        return result.size() > 0? Optional.of(result.get(0)): Optional.empty() ;
    }

    @Override
    @Transactional
    public WorkOrderHistory save(WorkOrderHistory workOrderHistory) {

        int id = jdbcTemplate.update("INSERT INTO WORK_ORDER_VALIDATION_HISTORY (WORK_ORDER_TYPE, DEPARTMENT, VALID) VALUES (?,?,?)"
                , new Object[]{workOrderHistory.getWorkOrderType(), workOrderHistory.getDepartment(), workOrderHistory.getValid()}
                , new int[]{Types.VARCHAR, Types.VARCHAR, Types.BIT}
        );
        workOrderHistory.setId(id);
        return workOrderHistory;
    }

    @Override
    public List<WorkOrderHistory> findAll() {
        return jdbcTemplate.query("SELECT * FROM WORK_ORDER_VALIDATION_HISTORY"
                , new  WorkOrderHistory.WorkOrderHistoryRowMapper()
        );
    }
}
