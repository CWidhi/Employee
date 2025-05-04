package com.Employee.Employee.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.Employee.models.Department;
import com.Employee.Employee.models.DeptManager;
import com.Employee.Employee.models.Employee;
import com.Employee.Employee.models.CompositeKey.DeptManagerId;
import com.Employee.Employee.repository.DepartmenRepository;
import com.Employee.Employee.repository.DeptManagerRepository;
import com.Employee.Employee.repository.EmployeeRepository;

@Service
public class DeptManagerService {
    @Autowired
    private DeptManagerRepository deptManagerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmenRepository departmenRepository; 

    public List<DeptManager> findByEmpNo(Integer empNo){
        return deptManagerRepository.findByEmpNo(empNo);
    }
    public List<DeptManager> findByDeptNo(String deptNo){
        return deptManagerRepository.findByDeptNo(deptNo);
    }
    public DeptManager create(DeptManager deptManager){
        Employee employee = employeeRepository.findById(deptManager.getEmpNo())
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        Department department = departmenRepository.findById(deptManager.getDeptNo())
            .orElseThrow(() -> new RuntimeException("Department not found"));

        deptManager.setEmployee(employee);
        deptManager.setDepartment(department);
        return deptManagerRepository.save(deptManager);
    }

    public boolean delete(Integer empNo, String deptNo, Date fromDate) {
    DeptManagerId id = new DeptManagerId(empNo, deptNo, fromDate);
        if (deptManagerRepository.existsById(id)) {
            deptManagerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
