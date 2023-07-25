package com.example.homework2_7collections2.service.impl;

import com.example.homework2_7collections2.employee.Employee;
import com.example.homework2_7collections2.service.DepartmentService;
import com.example.homework2_7collections2.service.EmployeeService;
import com.example.homework2_7collections2.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

public class DepartmentServiceTest {
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {

        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
        when(employeeService.getAllEmployees()).thenReturn(employeeList());

        departmentService = new DepartmentServiceImpl(employeeService);
    }

    private List<Employee> employeeList() {
        return List.of(new Employee("Ivan", "Ivanov", 5000, 1),
                new Employee("Petr", "Petrov", 3000, 2),
                new Employee("Taras", "Serdyuk", 6000, 1),
                new Employee("Ivan", "Serdyuk", 1000, 2));
    }

    @Test
    public void shouldReturnEmployeeMaxSalaryFromDep1() {
        Employee employee = departmentService.findEmployeeMaxSalary(1);

        Assertions.assertEquals("Taras", employee.getFirstName());
        Assertions.assertEquals("Serdyuk", employee.getLastName());
        Assertions.assertEquals(6000, employee.getSalary());
        Assertions.assertEquals(1, employee.getDepartment());
    }

    @Test
    public void shouldThrowExceptionWhenSearchEmployeeMaxSalaryFromDep3() {
        Assertions.assertThrows(RuntimeException.class, () ->
                departmentService.findEmployeeMaxSalary(3));
    }

    @Test
    public void shouldReturnEmployeeMinSalaryFromDep2() {
        Employee employee = departmentService.findEmployeeMinSalary(2);

        Assertions.assertEquals("Ivan", employee.getFirstName());
        Assertions.assertEquals("Serdyuk", employee.getLastName());
        Assertions.assertEquals(1000, employee.getSalary());
        Assertions.assertEquals(2, employee.getDepartment());
    }

    @Test
    public void shouldThrowExceptionWhenSearchEmployeeMinSalaryFromDep3() {
        Assertions.assertThrows(RuntimeException.class, () ->
                departmentService.findEmployeeMinSalary(3));
    }

    @Test
    public void shouldReturnEmployeesByDepartment1() {
        Collection<Employee> employees = departmentService.getEmployeesByDepartment(1);
        Collection<Employee> employeesResult = employeeList()
                .stream()
                .filter(employee -> employee.getDepartment() == 1)
                .collect(Collectors.toList());

        Assertions.assertEquals(employeesResult, employees);
    }
}