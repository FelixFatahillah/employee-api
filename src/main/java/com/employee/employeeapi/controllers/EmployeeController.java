package com.employee.employeeapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeeapi.dtos.CreateEmployeeDto;
import com.employee.employeeapi.models.entities.EmployeeData;
import com.employee.employeeapi.models.entities.EmployeeGrade;
import com.employee.employeeapi.services.EmployeeDataService;
import com.employee.employeeapi.services.EmployeeGradeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeDataService employeeDataService;
    @Autowired
    private EmployeeGradeService employeeGradeService;

    private final JdbcTemplate jdbcTemplate;

    public EmployeeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public ResponseEntity<CreateEmployeeDto> create(@Valid @RequestBody EmployeeData employee, Errors errors){
        // Check body request
        CreateEmployeeDto response = new CreateEmployeeDto();
        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                response.getMessage().add(error.getDefaultMessage());
            }
            response.setStatus("failed");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

        Long idToFind = employee.getGradeCode().getId();
        String sql = "SELECT COUNT(*) FROM employee_grade WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, idToFind);

        if (count == 0){
            response.setStatus("failed");
            String message = "grade id not found";
            response.getMessage().add(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        EmployeeGrade gradeUser = employeeGradeService.findOne(idToFind);

        // calculate update Bonus
        double bonus = employee.getSalary() * ((double)gradeUser.getBonusPersentage()/100.0);
        System.out.println("bonusss  "+ bonus);
        int total_bonus = (int)employee.getSalary() + (int)bonus;
        employee.setTotalBonus(total_bonus);

        // Create employee data
        employeeDataService.save(employee);

        response.setStatus("success");
        String message = "success to add "+ employee.getName();
        response.getMessage().add(message);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Iterable<EmployeeData> findAll(){
        return employeeDataService.findAll();
    }

    @PutMapping
    public ResponseEntity<CreateEmployeeDto> update(@RequestBody EmployeeData employee){
        CreateEmployeeDto response = new CreateEmployeeDto();

        Long idToFind = employee.getId();
        String sql = "SELECT COUNT(*) FROM employee_data WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, idToFind);

        if (count == 0){
            response.setStatus("failed");
            String message = "employee data not found";
            response.getMessage().add(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        EmployeeGrade gradeUser = employeeGradeService.findOne(idToFind);

        // calculate update Bonus
        double bonus = employee.getSalary() * ((double)gradeUser.getBonusPersentage()/100.0);
        int total_bonus = (int)employee.getSalary() + (int)bonus;
        employee.setTotalBonus(total_bonus);

        // update data
        employeeDataService.save(employee);

        response.setStatus("success");
        String message = "success to update "+ employee.getName();
        response.getMessage().add(message);
        return ResponseEntity.ok(response);
    }
}
