package com.diallo.mockExam.repository;

import com.diallo.mockExam.model.RetirementPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RetirementPlanRepository extends JpaRepository<RetirementPlan, Long> {
}
