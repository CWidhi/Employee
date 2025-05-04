package com.Employee.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Employee.models.Department;

public interface DepartmenRepository extends JpaRepository<Department, String> {
    boolean existsByDeptName(String deptName);
}
