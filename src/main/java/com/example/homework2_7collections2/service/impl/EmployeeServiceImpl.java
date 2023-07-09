package com.example.homework2_7collections2.service.impl;

import com.example.homework2_7collections2.service.EmployeeService;

import com.example.homework2_7collections2.employee.Employee;
import com.example.homework2_7collections2.exception.EmployeeAlreadyAddedException;
import com.example.homework2_7collections2.exception.EmployeeNotFoundException;
import com.example.homework2_7collections2.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;
    static int maxCount = 10;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }
    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.size() >= maxCount) {
            throw new EmployeeStorageIsFullException();
        } else if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeList.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employeeList.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeList.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> showEmployeeList() {
        return Collections.unmodifiableList(employeeList);
    }
}