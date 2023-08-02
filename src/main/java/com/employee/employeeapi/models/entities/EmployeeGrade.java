package com.employee.employeeapi.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employee_grade")
public class EmployeeGrade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "name is required")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "bonus persentage is required")
    private Integer bonusPersentage;

    public EmployeeGrade() {
    }

    public EmployeeGrade(Long id, String name, Integer bonusPersentage) {
        this.id = id;
        this.name = name;
        this.bonusPersentage = bonusPersentage;
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

    public Integer getBonusPersentage() {
        return bonusPersentage;
    }

    public void setBonusPersentage(Integer bonusPersentage) {
        this.bonusPersentage = bonusPersentage;
    }

    
}
