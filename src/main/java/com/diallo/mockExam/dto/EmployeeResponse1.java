package com.diallo.mockExam.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse1 {
    private Long id;
    private String firstName ;
    private String lastName ;
    private Double yearlySalary;
    RetirementPlanResponse1 retirementPlan;

}
