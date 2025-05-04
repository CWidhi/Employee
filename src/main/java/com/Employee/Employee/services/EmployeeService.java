package com.Employee.Employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Employee.Employee.models.Employee;
import com.Employee.Employee.models.Salary;
import com.Employee.Employee.models.Title;
import com.Employee.Employee.models.DTO.EmployeeDTO;
import com.Employee.Employee.repository.EmployeeRepository;
import com.Employee.Employee.repository.SalaryRepository;
import com.Employee.Employee.repository.TitleRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private TitleRepository titleRepository;
    
    public List<Employee> getAllEmployee(){
        return employeeRepo.findAll();
    }

    public Employee getById(Integer empNo) {
        return employeeRepo
                .findById(empNo)
                .orElseThrow(()
                        -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Employee dengan ID " + empNo + " tidak ditemukan"
                            )
                );
    }

    public Employee create(@Valid Employee employee){
        if (employee.getEmpNo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee ID tidak boleh null");
        }

        if (employeeRepo.existsById(employee.getEmpNo())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Employee dengan ID " + employee.getEmpNo() + " sudah ada");
        }

        return employeeRepo.save(employee);
    }

    public Employee update(Integer empNo, @Valid Employee employee){
        Employee existingEmployee =  employeeRepo
        .findById(empNo)
        .orElseThrow(()
                -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee dengan ID " + empNo + " tidak ditemukan"
                    )
        );

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setBirthDate(employee.getBirthDate());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setHireDate(employee.getHireDate());

        return employeeRepo.save(existingEmployee);
    }

    public Employee updatePartial(Integer empNo, @Valid EmployeeDTO employee) {
        Employee existingEmployee = employeeRepo
                .findById(empNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee dengan ID " + empNo + " tidak ditemukan"));

        if (employee.getFirstName() != null) {
            existingEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            existingEmployee.setLastName(employee.getLastName());
        }
        if (employee.getBirthDate() != null) {
            existingEmployee.setBirthDate(employee.getBirthDate());
        }
        if (employee.getGender() != null) {
            existingEmployee.setGender(employee.getGender());
        }
        if (employee.getHireDate() != null) {
            existingEmployee.setHireDate(employee.getHireDate());
        }

        return employeeRepo.save(existingEmployee);
    }

    public void delete(Integer empNo){
        Employee existingEmployee = employeeRepo.findById(empNo)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Employee dengan ID " + empNo + " tidak ditemukan"
        ));

        employeeRepo.delete(existingEmployee);
    }

    public List<Salary> getByEmpNo(Integer empNo) {
        return salaryRepository.findByEmpNo(empNo);
    }

    public List<Title> getTitleByEmpNo(Integer empNo){
        return titleRepository.findByEmpNo(empNo);
    }
}
