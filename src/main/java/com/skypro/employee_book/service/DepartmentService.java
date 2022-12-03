package com.skypro.employee_book.service;

import com.skypro.employee_book.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public int getSumSalaryDepartment(int department){
        return getEmployeeDepartment(department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getMaxSalaryDepartment(int department){
        return getEmployeeDepartment(department)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(RuntimeException::new);
    }

    public int getMinSalaryDepartment(int department){
        return getEmployeeDepartment(department)
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(RuntimeException::new);
    }
    public List<Employee> getEmployeeByDepartment(int department){
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());
    }
    public Map<Integer,List<Employee>> getEmployeesGroupedByDepartment(){
        return employeeService.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    public Stream<Employee> getEmployeeDepartment(int department){
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartment()==department);
    }

}
