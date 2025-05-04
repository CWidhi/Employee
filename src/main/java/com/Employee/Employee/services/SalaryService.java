package com.Employee.Employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.Employee.models.Employee;
import com.Employee.Employee.models.Salary;
import com.Employee.Employee.models.CompositeKey.SalaryId;
import com.Employee.Employee.repository.EmployeeRepository;
import com.Employee.Employee.repository.SalaryRepository;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Salary> getAll(){
        return salaryRepository.findAll();
    }

    public Optional<Salary> getById(SalaryId id){
        return salaryRepository.findById(id);
    }

    public Salary create(Salary salary) {
        Employee employee = employeeRepository.findById(salary.getEmpNo())
            .orElseThrow(() -> new RuntimeException("Employee not found"));

        salary.setEmployee(employee);
        return salaryRepository.save(salary);
    }

    public Salary update(SalaryId id, Salary salary){
        if (salaryRepository.existsById(id)) {
            return salaryRepository.save(salary);
        }
        throw new RuntimeException("Salary with id " + id + " not found.");
    }
    

    public void delete(SalaryId id) {
        salaryRepository.deleteById(id);
    }
}
