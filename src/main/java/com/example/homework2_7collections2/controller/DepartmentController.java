package com.example.homework2_7collections2.controller;


import com.example.homework2_7collections2.employee.Employee;
import com.example.homework2_7collections2.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalary(@RequestParam Integer department) {
        return departmentService.findEmployeeMaxSalary(department);
    }
    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalary(@RequestParam Integer department) {
        return departmentService.findEmployeeMinSalary(department);
    }
    @GetMapping(value = "/all", params = "department")
    public Collection<Employee> getEmployeesByDepartment(@RequestParam Integer department) {
        return departmentService.getEmployeesByDepartment(department);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return departmentService.getEmployeesGroupedByDepartment();
    }
}