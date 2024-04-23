package com.diallo.mockExam.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RequestDTO {

    private String firstName ;
    private String lastName ;
    private Double yearlySalary;
    private String referenceNumber;
    private LocalDate retirementDate;
    private LocalDate enrollmentDate;
    private Double monthlyContribution;

}
