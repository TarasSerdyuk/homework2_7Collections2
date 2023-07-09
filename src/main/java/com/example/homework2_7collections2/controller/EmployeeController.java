package com.example.homework2_7collections2.controller;

import com.example.homework2_7collections2.employee.Employee;
import com.example.homework2_7collections2.exception.EmployeeAlreadyAddedException;
import com.example.homework2_7collections2.exception.EmployeeNotFoundException;
import com.example.homework2_7collections2.exception.EmployeeStorageIsFullException;
import com.example.homework2_7collections2.service.EmployeeService;
import com.example.homework2_7collections2.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("//employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            return employeeService.add(firstName, lastName);
        } catch (EmployeeStorageIsFullException e) {
            System.out.println("Превышен лимит сотрудников");
            throw e;
        } catch (EmployeeAlreadyAddedException e) {
            System.out.println("Такой сотрудник уже существует");
            throw e;
        }
    }
    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            return employeeService.remove(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Сотрудник не найден");
            throw e;
        }
    }
    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            return employeeService.find(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Сотрудник не найден");
            throw e;
        }
    }

    @GetMapping
    public Collection<Employee> showEmployeeList() {
        return employeeService.showEmployeeList();
    }
}