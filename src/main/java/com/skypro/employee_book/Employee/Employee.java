package com.skypro.employee_book.Employee;

import com.skypro.employee_book.NameOfEmployees.NamesOfEmployees;

public class Employee {
    private final String employeeName;
    private int department;
    private double salary;
    private static int counter=0;
    private final int id;
    public Employee(String lastName, String firstName, String patronymic, int department, double salaries){
        this.employeeName= String.valueOf(new NamesOfEmployees(lastName, firstName, patronymic));
        this.department=department;
        this.salary= salaries;
        this.id=counter++;
    }
    public int getId() {return id;}
    public String getEmployeeName() {return employeeName;}
    public int getDepartment() {return department;}
    public void setDepartment(int department) {this.department = department;
    }
    public double getSalary() {return salary;}
    public void setSalary(double salary) {this.salary = salary;
    }

    @Override
    public String toString() {
        return "Ф.И.О.: " + employeeName  +
                ". Отдел № " + department +
                ". Зарплата: " + salary + " рублей. " +
                "id сотрудника: " + id;
    }

    public String substring(int i, int i1) {
        return null;
    }
}
