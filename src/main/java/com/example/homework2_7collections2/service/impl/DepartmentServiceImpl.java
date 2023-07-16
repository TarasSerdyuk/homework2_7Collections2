package com.example.homework2_7collections2.service.impl;

import com.example.homework2_7collections2.employee.Employee;
import com.example.homework2_7collections2.service.DepartmentService;
import com.example.homework2_7collections2.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeMaxSalary(Integer department) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("Нет сотрудника с максимальной зарплатой"));
    }

    @Override
    public Employee findEmployeeMinSalary(Integer department) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException("Нет сотрудника с минимальной зарплатой"));
    }

    @Override
    public Collection<Employee> getEmployeesByDepartment(Integer department) {
        return employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
