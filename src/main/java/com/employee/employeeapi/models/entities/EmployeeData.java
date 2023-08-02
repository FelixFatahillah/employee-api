package com.employee.employeeapi.models.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employee_data")
public class EmployeeData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotNull(message = "salary is required")
    private Integer salary;

    @ManyToOne
    private EmployeeGrade gradeCode;

    private Integer totalBonus;

    public EmployeeData() {
    }
    
    public EmployeeData(Long id, @NotEmpty(message = "name is required") String name,
            @NotNull(message = "salary is required") Integer salary, EmployeeGrade gradeCode, Integer totalBonus) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.gradeCode = gradeCode;
        this.totalBonus = totalBonus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public EmployeeGrade getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(EmployeeGrade gradeCode) {
        this.gradeCode = gradeCode;
    }

    public Integer getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(Integer totalBonus) {
        this.totalBonus = totalBonus;
    }

    
}
