package com.Employee.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Employee.models.Salary;
import com.Employee.Employee.models.CompositeKey.SalaryId;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {
    List<Salary> findByEmpNo(Integer empNo);
}
