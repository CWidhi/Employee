package com.Employee.Employee.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.Employee.models.Department;
import com.Employee.Employee.models.DeptEmp;
import com.Employee.Employee.models.Employee;
import com.Employee.Employee.models.CompositeKey.DeptEmpId;
import com.Employee.Employee.repository.DepartmenRepository;
import com.Employee.Employee.repository.DeptEmpRepository;
import com.Employee.Employee.repository.EmployeeRepository;

@Service
public class DeptEmpService {
    @Autowired
    private DeptEmpRepository deptEmpRepo;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmenRepository departmenRepository;

    public List<DeptEmp> findByEmpNo(Integer empNo) {
        return deptEmpRepo.findByEmpNo(empNo);
    }

    public List<DeptEmp> findByDeptNo(String deptNo) {
        return deptEmpRepo.findByDeptNo(deptNo);
    }

    public DeptEmp create(DeptEmp deptEmp) {
        Employee employee = employeeRepository.findById(deptEmp.getEmpNo())
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        Department department = departmenRepository.findById(deptEmp.getDeptNo())
            .orElseThrow(() -> new RuntimeException("Department not found"));

        deptEmp.setEmployee(employee);
        deptEmp.setDepartment(department);
        return deptEmpRepo.save(deptEmp);
    }

    public boolean delete(Integer empNo, String deptNo, Date fromDate) {
        DeptEmpId id = new DeptEmpId(empNo, deptNo, fromDate);
        if(deptEmpRepo.existsById(id)){
            deptEmpRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
