package com.Employee.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Employee.models.DeptEmp;
import com.Employee.Employee.models.CompositeKey.DeptEmpId;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, DeptEmpId>{
    List<DeptEmp> findByEmpNo(Integer empNo);
    List<DeptEmp> findByDeptNo(String deptNo);
}
