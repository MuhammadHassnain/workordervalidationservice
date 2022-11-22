package com.goddev.workorder.validationservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Part{
    @JsonProperty("inventory_number")
    public String inventoryNumber;
    public String name;
    public int count;
}
