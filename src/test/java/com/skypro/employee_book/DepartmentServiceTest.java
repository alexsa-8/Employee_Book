package com.skypro.employee_book;

import com.skypro.employee_book.model.Employee;
import com.skypro.employee_book.service.DepartmentService;
import com.skypro.employee_book.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;
    private List<Employee> employeeList;

    @BeforeEach
    public void workEmployees(){
        Employee employee=new Employee("Иван","Иванов",1,10000);
        Employee employee1=new Employee("Пётр","Петров",1,12000);
        Employee employee2=new Employee("Сидор","Сидоров",2,14000);
        Employee employee3=new Employee("Павел","Павлов",2,16000);
        employeeList=List.of(employee,employee1,employee2,employee3);
    }

    @Test
    public void returnsListOfEmployeesByDepartment(){
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        final int department=1;
        final List<Employee> employees=employeeList.stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());
        final List<Employee> expected=departmentService.getEmployeeByDepartment(department);
        Assertions.assertEquals(expected, employees);
    }

    @Test
    public void returnEmployeesGroupedByDepartment(){
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        final Map<Integer, List<Employee>> employees= employeeService.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        final Map<Integer, List<Employee>> expected=departmentService.getEmployeesGroupedByDepartment();
        Assertions.assertEquals(expected, employees);
    }

    @Test
    public void returnsAmountOfDepartmentSalaries(){
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        final int department=1;
        final int employees= employeeList.stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .sum();
        final int expected=departmentService.getSumSalaryDepartment(department);
        Assertions.assertEquals(expected, employees);
    }

    @Test
    public void gettingAnEmployeeWithMinimumWage(){
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        final int department=1;
        final int employees= employeeList.stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(RuntimeException::new);
        final int expected=departmentService.getMinSalaryDepartment(department);
        Assertions.assertEquals(expected, employees);
    }
    @Test
    public void gettingAnEmployeeWithMaximumSalary(){
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        final int department=1;
        final int employees= employeeList.stream()
                .filter(employee -> employee.getDepartment()==department)
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(RuntimeException::new);
        final int expected=departmentService.getMaxSalaryDepartment(department);
        Assertions.assertEquals(expected, employees);
    }
}
