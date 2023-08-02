package com.employee.employeeapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.employeeapi.models.entities.EmployeeGrade;
import com.employee.employeeapi.repositories.EmployeeGradeRepository;

@Service
@Transactional
public class EmployeeGradeService {
    @Autowired
    private EmployeeGradeRepository employeeGradeRepository;

    public EmployeeGrade findOne(Long id){
        return employeeGradeRepository.findById(id).get();
    }

    public EmployeeGrade save(EmployeeGrade employeeGrade){
        return employeeGradeRepository.save(employeeGrade);
    }

    public Iterable<EmployeeGrade> findAll(){
        return employeeGradeRepository.findAll();
    }
}
