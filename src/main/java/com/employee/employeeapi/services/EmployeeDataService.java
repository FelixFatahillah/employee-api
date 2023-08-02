package com.employee.employeeapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.employeeapi.models.entities.EmployeeData;
import com.employee.employeeapi.repositories.EmployeeDataRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeDataService {
    @Autowired
    private EmployeeDataRepository employeeDataRepository;

    public EmployeeData save(EmployeeData employee){
        return employeeDataRepository.save(employee);
    }

    public Iterable<EmployeeData> findAll(){
        return employeeDataRepository.findAll();
    }
}
