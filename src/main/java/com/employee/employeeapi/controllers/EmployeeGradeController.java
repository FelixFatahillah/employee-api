package com.employee.employeeapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeeapi.dtos.CreateEmployeeDto;
import com.employee.employeeapi.models.entities.EmployeeGrade;
import com.employee.employeeapi.services.EmployeeGradeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee-grade")
public class EmployeeGradeController {
    @Autowired
    private EmployeeGradeService employeeGradeService;

    private final JdbcTemplate jdbcTemplate;

    public EmployeeGradeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public ResponseEntity<CreateEmployeeDto> create(@Valid @RequestBody EmployeeGrade employeeGrade, Errors errors){
        // Check body request
        CreateEmployeeDto response = new CreateEmployeeDto();
        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                response.getMessage().add(error.getDefaultMessage());
            }
            response.setStatus("failed");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

        String gradeName = employeeGrade.getName();
        String sql = "SELECT COUNT(*) FROM employee_grade WHERE name = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, gradeName);

        if (count != 0){
            response.setStatus("failed");
            String message = "grade already created";
            response.getMessage().add(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        employeeGradeService.save(employeeGrade);

        response.setStatus("success");
        String message = "success to add "+ employeeGrade.getName();
        response.getMessage().add(message);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Iterable<EmployeeGrade> findAll(){
        return employeeGradeService.findAll();
    }
}
