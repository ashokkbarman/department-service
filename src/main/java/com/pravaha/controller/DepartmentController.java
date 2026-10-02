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
import com.pravaha.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

   
    rivate final DepartmentRepository departmentRepository;

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
}
