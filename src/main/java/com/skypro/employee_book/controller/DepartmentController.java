package com.skypro.employee_book.controller;

import com.skypro.employee_book.model.Employee;
import com.skypro.employee_book.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private String id;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeByDepartment(@PathVariable("id") int id){

        return this.departmentService.getEmployeeByDepartment(id);
    }
    @GetMapping("/{id}/salary/sum")
    public int getSumSalaryDepartment(@PathVariable("id") int id){
        return this.departmentService.getSumSalaryDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public int getMaxSalaryDepartment(@PathVariable("id") int id){
        return this.departmentService.getMaxSalaryDepartment(id);
    }
    @GetMapping("/{id}/salary/min")
    public int getMinSalaryDepartment(@PathVariable("id") int id){
        return this.departmentService.getMinSalaryDepartment(id);
    }

    @GetMapping("/employees/grouped")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment(){
        return this.departmentService.getEmployeesGroupedByDepartment();
    }
}
