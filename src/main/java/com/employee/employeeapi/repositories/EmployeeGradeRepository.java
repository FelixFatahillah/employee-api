package com.employee.employeeapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.employee.employeeapi.models.entities.EmployeeGrade;

public interface EmployeeGradeRepository extends CrudRepository<EmployeeGrade, Long> {

}
