package com.employee.employeeapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.employee.employeeapi.models.entities.EmployeeData;

public interface EmployeeDataRepository extends CrudRepository<EmployeeData, Long> {
    
}
