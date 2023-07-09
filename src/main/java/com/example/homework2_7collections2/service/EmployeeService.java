package com.example.homework2_7collections2.service;

import com.example.homework2_7collections2.employee.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);

    Collection<Employee> showEmployeeList();
}