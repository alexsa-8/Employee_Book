package com.skypro.employee_book.service;

import com.skypro.employee_book.model.Employee;
import com.skypro.employee_book.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) throws IllegalAccessException {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalAccessException("Введите имя и фамилию");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;

    }

    public int getSalarySum() {
        return employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getMinSalary() {
        var employeeMinSalary = employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .min()
                .orElse(0);
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employeeMinSalary == employee.getSalary()) {
                list.add(employee);
            }
        }
        return employeeMinSalary;
    }

    public int getMaxSalary() {
        var employeeMaxSalary = employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0);
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employeeMaxSalary == employee.getSalary()) {
                list.add(employee);
            }
        }
        return employeeMaxSalary;
    }

    public List<Employee> getEmployeesAboveAverageSalary() {
        int averageSalary = (int) employees.values()
                .stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow();
        return employees.values()
                .stream()
                .filter(employee -> averageSalary < employee.getSalary())
                .collect(Collectors.toList());
    }
}
