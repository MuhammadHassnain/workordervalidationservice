package com.goddev.workorder.validationservice.service;

import com.goddev.workorder.validationservice.dto.Part;
import com.goddev.workorder.validationservice.dto.WorkOrder;
import com.goddev.workorder.validationservice.dto.WorkOrderType;
import com.goddev.workorder.validationservice.exception.InvalidParameterException;
import com.goddev.workorder.validationservice.exception.InvalidRequestException;
import com.goddev.workorder.validationservice.exception.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class WorkOrderValidationServiceTest {

    private WorkOrder workOrder;

    private final WorkOrderValidationService workOrderValidationService;

    @Autowired
    WorkOrderValidationServiceTest(WorkOrderValidationService workOrderValidationService) {
        this.workOrderValidationService = workOrderValidationService;
    }


    @BeforeEach
    void setUp() {
        workOrder = WorkOrder.builder().build();
    }

    @Test
    void workOrderRequestMustHaveValidWorkOrderType(){


        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));

    }

    @Test
    void mustBeValidAnalysisWorkOrder(){
        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("100"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setType(WorkOrderType.ANALYSIS);


        assertTrue(workOrderValidationService.validate(workOrder));
    }
    @Test
    void mustBeInValidAnalysisWorkOrderDueToStartDate(){
        workOrder.setStartDate(new GregorianCalendar(2023, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setType(WorkOrderType.ANALYSIS);


        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }
    @Test
    void mustBeInValidAnalysisWorkOrderDueToEndDate(){
        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setType(WorkOrderType.ANALYSIS);


        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }


    @Test
    void mustBeInValidAnalysisWorkOrderDueToCurrency(){
        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD1");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setType(WorkOrderType.ANALYSIS);


        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }

    @Test
    void mustBeInValidAnalysisWorkOrderDueToCostAndStartDate(){
        workOrder.setStartDate(new GregorianCalendar(2023, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("0"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setType(WorkOrderType.ANALYSIS);


        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }

    @Test
    void mustBeInValidAnalysisWorkOrderDueToDepartmentAndCost(){
        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("0"));
        workOrder.setDepartment("GOoD analysis departmen1t");

        workOrder.setType(WorkOrderType.ANALYSIS);


        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }



    @Test
    void mustBeValidRepairWorkOrder(){
        workOrder.setType(WorkOrderType.REPAIR);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 19).getTime());
        workOrder.setTestDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 20).getTime());
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));
        workOrder.setResponsiblePerson("Hassnain");



        assertTrue(workOrderValidationService.validate(workOrder));
    }


    @Test
    void mustBeInValidRepairWorkOrderDueToGeneralRule(){
        workOrder.setType(WorkOrderType.REPAIR);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD1");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 19).getTime());
        workOrder.setTestDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 20).getTime());
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));
        workOrder.setResponsiblePerson("Hassnain");



        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }
    @Test
    void mustBeInValidRepairWorkOrderDueToAnalysisDate(){
        workOrder.setType(WorkOrderType.REPAIR);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setTestDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 20).getTime());
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));
        workOrder.setResponsiblePerson("Hassnain");



        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }
    @Test
    void mustBeInValidRepairWorkOrderDueToTestDate(){
        workOrder.setType(WorkOrderType.REPAIR);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 19).getTime());
        workOrder.setTestDate(new GregorianCalendar(2011, Calendar.NOVEMBER, 20).getTime());
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));
        workOrder.setResponsiblePerson("Hassnain");



        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }
    @Test
    void mustBeInValidRepairWorkOrderDueToParts(){
        workOrder.setType(WorkOrderType.REPAIR);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 19).getTime());
        workOrder.setTestDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 20).getTime());
        workOrder.setParts(List.of());
        workOrder.setResponsiblePerson("Hassnain");



        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }

    @Test
    void mustBeInValidRepairWorkOrderDueToMissingResponsiblePerson(){
        workOrder.setType(WorkOrderType.REPAIR);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 19).getTime());
        workOrder.setTestDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 20).getTime());
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));



        assertThrows(InvalidRequestException.class, ()-> workOrderValidationService.validate(workOrder));
    }


    @Test
    void mustBeValidReplacementWorkOrder(){
        workOrder.setType(WorkOrderType.REPLACEMENT);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setFactoryName("Factory name");
        workOrder.setFactoryOrderNumber("FE12345678");
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));


        assertTrue(workOrderValidationService.validate(workOrder));


    }

    @Test
    void mustBeInValidReplacementWorkOrderDueToFactoryOrderNumber(){
        workOrder.setType(WorkOrderType.REPLACEMENT);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setFactoryName("Factory name");
        workOrder.setFactoryOrderNumber("F123456781");
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));


        assertThrows(InvalidRequestException.class,()-> workOrderValidationService.validate(workOrder));


    }

    @Test
    void mustBeInValidReplacementWorkOrderDueToFactoryName(){
        workOrder.setType(WorkOrderType.REPAIR);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setFactoryName(" ");
        workOrder.setFactoryOrderNumber("FE12345678");
        Part part = Part.builder().inventoryNumber("123").build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));


        assertThrows(InvalidRequestException.class,()-> workOrderValidationService.validate(workOrder));


    }
    @Test
    void mustBeInValidReplacementWorkOrderDueToMissingPartInventoryNumber(){
        workOrder.setType(WorkOrderType.REPAIR);

        workOrder.setStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 18).getTime());
        workOrder.setEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 30).getTime());
        workOrder.setCurrency("USD");
        workOrder.setCost(new BigDecimal("10"));
        workOrder.setDepartment("GOoD analysis department");

        workOrder.setFactoryName("Factory name");
        workOrder.setFactoryOrderNumber("FE12345678");
        Part part = Part.builder().build();
        Part par1 = Part.builder().inventoryNumber("345").build();
        workOrder.setParts(List.of(part,par1));


        assertThrows(InvalidRequestException.class,()-> workOrderValidationService.validate(workOrder));


    }

}