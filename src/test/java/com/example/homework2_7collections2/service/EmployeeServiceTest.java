package com.example.homework2_7collections2.service;

import com.example.homework2_7collections2.employee.Employee;
import com.example.homework2_7collections2.exception.EmployeeAlreadyAddedException;
import com.example.homework2_7collections2.exception.EmployeeNotFoundException;
import com.example.homework2_7collections2.service.impl.EmployeeServiceImpl;
import com.example.homework2_7collections2.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployeeWhenItDoesntExist() {
        Employee employee = new Employee("Ivan", "Ivanov", 5000, 1);
        employeeService.addEmployee(employee);

        Employee employeeResult = employeeService.findEmployee("Ivan", "Ivanov");

        Assertions.assertEquals(employee, employeeResult);
    }

    @Test
    public void shouldThrowExceptionWhenEmployeeExists() {
        Employee employee = new Employee("Ivan", "Ivanov", 2000, 1);
        employeeService.addEmployee(employee);

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee(employee));
    }

    @Test
    public void shouldRemoveEmployeeWhenItDoesntExist() {
        Employee employee = new Employee("Ivan", "Serduyk", 5000, 1);
        employeeService.addEmployee(employee);
        employeeService.removeEmployee("Ivan", "Ivanov");

        Assertions.assertFalse(employeeService.getAllEmployees().contains(employee));
    }

    @Test
    public void shouldThrowExceptionWhenEmployeeDoesntExistsWhileRemove() {

        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.removeEmployee("Ivan", "Ivanov"));
    }

    @Test
    public void shouldThrowExceptionWhenEmployeeDoesntExistsWhileFind() {

        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee("Ivan", "Ivanov"));
    }
}