package com.diallo.mockExam.service;


import com.diallo.mockExam.config.CustumMapper;
import com.diallo.mockExam.dto.EmployeeResponse1;
import com.diallo.mockExam.dto.RequestDTO;
import com.diallo.mockExam.exception.EmployeeNotFoundException;
import com.diallo.mockExam.model.Employee;
import com.diallo.mockExam.model.RetirementPlan;
import com.diallo.mockExam.repository.EmployeeRepository;
import com.diallo.mockExam.utility.HelperFunctions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    public List<EmployeeResponse1> getAllEmployee() {

        var result =  HelperFunctions.sortEmployeeByFirstName(employeeRepository.findAll());

        return result.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponse1.class)).toList();
    }

    public EmployeeResponse1 getEmployeeById(Long employeeId) throws EmployeeNotFoundException {

        var employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException("employee with " + employeeId + " not found")
        );
        return modelMapper.map(employee, EmployeeResponse1.class);
    }

    public List<EmployeeResponse1> getUpcomingEmployees(){
        List<EmployeeResponse1> employees = getAllEmployee();
        List<EmployeeResponse1> upcomingEmployees = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfNextMonth = today.plusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfNextMonth =
                today.plusMonths(1).withDayOfMonth(31).minusDays(1);

        for (var employee : employees) {
            if(
                    employee.getRetirementPlan().getRetirementDate()
                            .compareTo(firstDayOfNextMonth) >=0 &&
                            employee.getRetirementPlan().getRetirementDate()
                                    .compareTo(lastDayOfNextMonth) <= 0
            ){
                upcomingEmployees.add(employee);
            }
        }
        // Sort the retirees by retirement date
        upcomingEmployees.sort((e1, e2) -> e1.getRetirementPlan().getRetirementDate().compareTo(e2.getRetirementPlan().getRetirementDate()));
        return upcomingEmployees;
    }

    public Employee addEmployeeRetirementPlan(RequestDTO requestDTO) {

        var employee = modelMapper.map(requestDTO, Employee.class);
        var retirementPlan = modelMapper.map(requestDTO, RetirementPlan.class);

        employee.setRetirementPlan(retirementPlan);
        retirementPlan.setEmployee(employee);

//        Employee employee = new Employee();
//        employee.setFirstName(retirementPlanDTO.getFirstName());
//        employee.setLastName(retirementPlanDTO.getLastName());
//        employee.setYearlySalary(retirementPlanDTO.getYearlySalary());
//
//        RetirementPlan  retirementPlan = new RetirementPlan();
//        retirementPlan.setReferenceNumber(retirementPlanDTO.getReferenceNumber());
//        retirementPlan.setRetirementDate(retirementPlanDTO.getRetirementDate());
//        retirementPlan.setEnrollmentDate(retirementPlanDTO.getEnrollmentDate());
//        retirementPlan.setMonthlyContribution(retirementPlanDTO.getMonthlyContribution());
//        retirementPlan.setEmployee(employee);
        return  employeeRepository.save(employee);
    }
}
