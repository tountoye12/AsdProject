package com.diallo.mockExam.controller;


import com.diallo.mockExam.dto.EmployeeResponse1;
import com.diallo.mockExam.dto.EmployeeResponse2;
import com.diallo.mockExam.dto.RequestDTO;
import com.diallo.mockExam.exception.EmployeeNotFoundException;
import com.diallo.mockExam.model.Employee;
import com.diallo.mockExam.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /*
    Implement a RESTful Web API endpoint url which presents the data of the Monthly
    Upcoming Retirees report, in JSON format. Note: This data should contain only the list of
    Employees whose retirement date is on or between the first and the last date of the
    next month. The Company requires this list to include the Retirement Plan data for each
    Employee and the list is to be displayed sorted in ascending order of the retirement
    dates
     */
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeResponse1>> getEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    /*
        2. Implement a RESTful Web API endpoint url which presents
        the RetirementPlan data in
        JSON format, for a given Employee by the employeeId. The Company requires this data
        to include the Employee information.
     */

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse1> getEmployee(@PathVariable Long employeeId)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    /*
    3. Implement a RESTful Web API endpoint url which presents the data of the Monthly
        Upcoming Retirees report, in JSON format. Note: This data should contain only the list of
        Employees whose retirement date is on or between the first and the last date of the
        next month. The Company requires this list to include the Retirement Plan data for each
        Employee and the list is to be displayed sorted in ascending order of the retirement
        dates.
     */

    @GetMapping("/upcoming")
    public ResponseEntity<List<EmployeeResponse1>> getUpcomingEmployees() {

        return  ResponseEntity.ok(employeeService.getUpcomingEmployees());
    }


    /*
           4. Implement a RESTful Web API endpoint url that adds a new Retirement
            Plan for an
        Employee into the system, upon receiving the
        following JSON-formatted data submitted
        to it via an HTTP POST request.
        New Employee-RetirementPlan Data in JSON format:
         {
         "firstName": "Anna",
         "lastName": "Smith",
         "yearlySalary": 150000.00,
         "referenceNumber": "SM1009",
         "enrollmentDate": "2023-08-16",
         "retirementDate": "2026-09-29",
         "monthlyContribution": null
         }
     */

    @PostMapping("/add")
    public ResponseEntity<Employee>
    addEmployeeRetirementPlan(@RequestBody @Valid RequestDTO requestDTO){
        return ResponseEntity.ok(employeeService.addEmployeeRetirementPlan(requestDTO));
    }


    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId){
        employeeService.delete(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }





}
