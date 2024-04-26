package com.diallo.mockExam.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "retirementplan")
public class RetirementPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String referenceNumber;
    @NotNull
    private LocalDate retirementDate;

    @NotNull
    private LocalDate enrollmentDate;

    private Double monthlyContribution;
    @OneToOne
    @JsonBackReference
    // add json back ref
    private Employee employee;
}
