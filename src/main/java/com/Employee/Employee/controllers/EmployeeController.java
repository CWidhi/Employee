package com.Employee.Employee.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Employee.models.Employee;
import com.Employee.Employee.models.Salary;
import com.Employee.Employee.models.Title;
import com.Employee.Employee.models.DTO.EmployeeDTO;
import com.Employee.Employee.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/{empNo}")
    public Employee getById(@PathVariable Integer empNo){
        return employeeService.getById(empNo);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Valid Employee employee){
        Employee created = employeeService.create(employee);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{empNo}")
    public ResponseEntity<Employee> update(@PathVariable Integer empNo, @RequestBody @Valid Employee employee){
        Employee update = employeeService.update(empNo, employee);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @PatchMapping("/{empNo}")
    public ResponseEntity<Employee> updatePartial(@PathVariable Integer empNo, @RequestBody EmployeeDTO dto) {
        Employee updatedEmployee = employeeService.updatePartial(empNo, dto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{empNo}")
    public ResponseEntity<String> delete(@PathVariable Integer empNo){
        employeeService.delete(empNo);
        return ResponseEntity.ok("Delete successfully");
    }

    @GetMapping("/saleries/{empNo}")
    public List<Salary> getByEmployee(@PathVariable Integer empNo) {
        return employeeService.getByEmpNo(empNo);
    }

    @GetMapping("/title/{empNo}")
    public List<Title> getTitleByEmployee(@PathVariable Integer empNo) {
        return employeeService.getTitleByEmpNo(empNo);
    }
    
}
