package com.example.homework2_7collections2.service;

import com.example.homework2_7collections2.employee.Employee;

import java.util.Collection;

public interface EmployeeService {

    void addEmployee(Employee employee);

    void removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> showEmployees();
    Collection<Employee> getAllEmployees();
}