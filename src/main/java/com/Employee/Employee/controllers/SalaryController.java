package com.Employee.Employee.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Employee.models.Salary;
import com.Employee.Employee.models.CompositeKey.SalaryId;
import com.Employee.Employee.services.SalaryService;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public List<Salary> getAll() {
        return salaryService.getAll();
    }

    @GetMapping("/{empNo}/{fromDate}")
    public ResponseEntity<?> getById(@PathVariable Integer empNo, @PathVariable String fromDate) {
        SalaryId id = new SalaryId(empNo, java.sql.Date.valueOf(fromDate));
        Optional<Salary> salaryOpt = salaryService.getById(id);

        if (salaryOpt.isPresent()) {
            Salary salary = salaryOpt.get();
            Map<String, Object> response = new HashMap<>();
            response.put("salary", salary.getSalary());
            response.put("fromDate", salary.getFromDate());
            response.put("toDate", salary.getToDate());

            Map<String, Object> employeeData = new HashMap<>();
            employeeData.put("firstName", salary.getEmployee().getFirstName());
            employeeData.put("lastName", salary.getEmployee().getLastName());

            response.put("employee", employeeData);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Salary not found");
        }
    }

    @PostMapping
    public Salary create(@RequestBody Salary salary) {
        return salaryService.create(salary);
    }

    @PutMapping("/{empNo}/{fromDate}")
    public Salary update(@PathVariable Integer empNo, @PathVariable String fromDate, @RequestBody Salary salary) {
        SalaryId id = new SalaryId(empNo, java.sql.Date.valueOf(fromDate));
        return salaryService.update(id, salary);
    }

    @DeleteMapping("/{empNo}/{fromDate}")
    public void delete(@PathVariable Integer empNo, @PathVariable String fromDate) {
        SalaryId id = new SalaryId(empNo, java.sql.Date.valueOf(fromDate));
        salaryService.delete(id);
    }

}
