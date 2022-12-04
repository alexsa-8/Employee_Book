package com.skypro.employee_book;

import com.skypro.employee_book.exception.NotAllDataHasBeenEntered;
import com.skypro.employee_book.model.Employee;
import com.skypro.employee_book.record.EmployeeRequest;
import com.skypro.employee_book.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private EmployeeService employeeService;
    private final List<Employee> employees;

    public EmployeeServiceTest(List<Employee> employees) {
        this.employees = employees;
    }

    @BeforeEach
    public void workEmployees(){
        this.employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("Иван","Иванов",1,10000),
                new EmployeeRequest("Пётр","Петров",1,12000),
                new EmployeeRequest("Сидор","Сидоров",2,14000),
                new EmployeeRequest("Павел","Павлов",2,16000)
        ).forEach(employeeRequest -> {
            try {
                employeeService.addEmployee(employeeRequest);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    public void addEmployee() throws IllegalAccessException {
        EmployeeRequest request=new EmployeeRequest("test", "test", 1 , 10000);
        Employee result=employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), result.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());
        Assertions.assertThat(employeeService.getAllEmployees()).contains(result);
    }
    @Test
    public void getSalarySum(){
        assertEquals(52000, employeeService.getSalarySum());
    }
    @Test
    public void getMaxSalary(){
        assertEquals(16000, employeeService.getMaxSalary());
    }
    @Test
    public void getMinSalary(){
        assertEquals(10000, employeeService.getMinSalary());
    }
    @Test
    public void getEmployeesAboveAverageSalary(){
        int averageSalary= (int) employees.stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElseThrow(NotAllDataHasBeenEntered::new);
        List<Employee> list=employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getSalary()>averageSalary)
                .collect(Collectors.toList());
        assertEquals(employeeService.getEmployeesAboveAverageSalary(), list);
    }
}
