package com.example.dao;

import com.example.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll() throws Exception;
    Employee findById(int id) throws Exception;
}
