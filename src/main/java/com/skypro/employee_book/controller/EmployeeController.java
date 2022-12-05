package com.skypro.employee_book.controller;

import com.skypro.employee_book.model.Employee;
import com.skypro.employee_book.record.EmployeeRequest;
import com.skypro.employee_book.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {

        return this.employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest)
            throws IllegalAccessException {
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSalarySum() {

        return this.employeeService.getSalarySum();
    }

    @GetMapping("/employees/salary/min")
    public int getMinSalary() {

        return this.employeeService.getMinSalary();
    }

    @GetMapping("/employees/salary/max")
    public int getMaxSalary() {

        return this.employeeService.getMaxSalary();
    }


    @GetMapping("/employees/high-salary")
    public List<Employee> getAllEmployeesWithAverageSalary() {
        return this.employeeService.getEmployeesAboveAverageSalary();
    }
}
