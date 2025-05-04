package com.Employee.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Employee.models.DeptManager;
import com.Employee.Employee.models.CompositeKey.DeptManagerId;

public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptManagerId> {
    List<DeptManager> findByEmpNo(Integer empNo);
    List<DeptManager> findByDeptNo(String deptNo);
}
