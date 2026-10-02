package com.pravaha.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pravaha.model.Department;

@Repository
public class DepartmentRepository {

    private List<Department> departments = new ArrayList<>();

    public List<Department> getAllDepartments() {
        return departments;
    }

    public Department addDepartment(Department department) {
        departments.add(department);
        return department;
    }
    public Department findById(long id) {
        return departments.stream()
            .filter(department -> department.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
