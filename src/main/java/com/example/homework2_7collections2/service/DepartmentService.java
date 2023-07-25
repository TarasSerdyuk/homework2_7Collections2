package com.example.homework2_7collections2.service;

import com.example.homework2_7collections2.employee.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findEmployeeMaxSalary(Integer department);
    Employee findEmployeeMinSalary(Integer department);
    Collection<Employee> getEmployeesByDepartment(Integer department);
    Map<Integer, List<Employee>> getEmployeesGroupedByDepartment();
}