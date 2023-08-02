package com.employee.employeeapi.dtos;

import java.util.ArrayList;
import java.util.List;

public class CreateEmployeeDto {
    private String status;
    private List<String> message = new ArrayList<>();;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<String> getMessage() {
        return message;
    }
    public void setMessage(List<String> message) {
        this.message = message;
    }
}
