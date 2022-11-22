package com.goddev.workorder.validationservice.exception;

public class HistoryNotFoundException extends RuntimeException{



    private String error;

    public HistoryNotFoundException(Integer id) {
        this.error = String.format("History With id %d not found" , id );
    }

    public String getError() {
        return error;
    }
}
