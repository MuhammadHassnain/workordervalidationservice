package com.goddev.workorder.validationservice.dto;

public enum WorkOrderType {


    ANALYSIS("ANALYSIS"),
    REPAIR("REPAIR"),
    REPLACEMENT("REPLACEMENT");


    private final String name;

    WorkOrderType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
