package com.goddev.workorder.validationservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class WorkOrderHistory {


    private Integer id;
    private String department;
    private String workOrderType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    private Boolean valid;


    public static class WorkOrderHistoryRowMapper implements RowMapper<WorkOrderHistory>{

        @Override
        public WorkOrderHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
            WorkOrderHistory workOrderHistory = new WorkOrderHistory();
            workOrderHistory.setId(rs.getInt("ID"));
            workOrderHistory.setWorkOrderType(rs.getString("WORK_ORDER_TYPE"));
            workOrderHistory.setDepartment(rs.getString("DEPARTMENT"));
            workOrderHistory.setValid(rs.getBoolean("VALID"));
            workOrderHistory.setCreatedAt(rs.getTimestamp("CREATED_AT"));
            return workOrderHistory;
        }
    }

}
