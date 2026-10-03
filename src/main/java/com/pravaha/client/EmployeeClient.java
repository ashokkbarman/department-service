package com.pravaha.client;

import java.util.List;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.pravaha.model.Employee;

@HttpExchange("/employee")
public interface EmployeeClient {

    @GetExchange("/department/{departmentId}")
    public List<Employee> getEmployeesByDepartmentId(@RequestAttribute("departmentId") long departmentId);

}
