package com.Employee.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Employee.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    
} 