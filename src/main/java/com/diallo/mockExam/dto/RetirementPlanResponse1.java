package com.diallo.mockExam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetirementPlanResponse1 {
    private Long id;
    private String referenceNumber;
    private LocalDate retirementDate;
    private LocalDate enrollmentDate;
    private Double monthlyContribution;
}
