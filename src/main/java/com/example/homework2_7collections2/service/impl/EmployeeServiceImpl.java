package com.example.homework2_7collections2.service.impl;

import org.apache.commons.lang3.StringUtils;
import com.example.homework2_7collections2.exception.InvalidNameException;
import com.example.homework2_7collections2.service.EmployeeService;
import com.example.homework2_7collections2.employee.Employee;
import com.example.homework2_7collections2.exception.EmployeeAlreadyAddedException;
import com.example.homework2_7collections2.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final Map<String, Employee> employeesMap;
    public EmployeeServiceImpl() {
        this.employeesMap = new HashMap<>();
    }



    @Override
    public void addEmployee(Employee employee) {
        validate(employee.getFirstName(), employee.getLastName());

        employee.setFirstName(StringUtils.capitalize(employee.getFirstName().toLowerCase()));
        employee.setLastName(StringUtils.capitalize(employee.getLastName().toLowerCase()));

        if (employeesMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        employeesMap.put(employee.getFullName(), employee);
    }




    @Override
    public void removeEmployee(String firstName, String lastName) {
        if (!employeesMap.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
        employeesMap.remove(firstName + lastName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        if (!employeesMap.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
        return employeesMap.get(firstName + lastName);
    }

    @Override
    public Collection<Employee> showEmployees() {
        return Collections.unmodifiableCollection(employeesMap.values());
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return employeesMap.values();
    }
    private void validate(String... names) {
        for (String name : names) {
            if (!StringUtils.isAlpha(name)) {
                throw new InvalidNameException("Name must contain only letters");
            }
        }
    }
}