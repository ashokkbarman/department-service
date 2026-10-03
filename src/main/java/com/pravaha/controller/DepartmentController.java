package com.pravaha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pravaha.model.Department;
import com.pravaha.model.Employee;
import com.pravaha.repository.DepartmentRepository;
import com.pravaha.client.EmployeeClient;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private EmployeeClient employeeClient;

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        logger.info("Adding department: {}", department);
        return departmentRepository.addDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        logger.info("Fetching all departments");
        return departmentRepository.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable long id) {
        logger.info("Fetching department with id: {}", id);
        return departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> getAllDepartmentsWithEmployees() {
        logger.info("Fetching all departments with employees");
        return departmentRepository.getAllDepartments().stream()
                .peek(department -> {
                    List<Employee> employees = employeeClient.getEmployeesByDepartmentId(department.getId()).stream()
                            .map(Employee.class::cast)
                            .toList();
                    department.setEmployees(employees);
                })
                .toList();

    }
}
