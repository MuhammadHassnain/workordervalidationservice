package com.goddev.workorder.validationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class WorkOrder {
    private WorkOrderType type;
    private String department;
    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty("end_date")
    private Date endDate;
    @JsonProperty("responsible_person")
    private String responsiblePerson;
    @JsonProperty("factory_name")
    private String factoryName;
    @JsonProperty("factory_order_number")
    private String factoryOrderNumber;
    private String currency;
    @JsonProperty("test_date")
    private Date testDate;
    @JsonProperty("analysis_date")
    private Date analysisDate;
    private BigDecimal cost;
    @JsonProperty("parts")
    private List<Part> parts;
}
