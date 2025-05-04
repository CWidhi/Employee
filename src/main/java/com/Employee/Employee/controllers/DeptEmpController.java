package com.Employee.Employee.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Employee.models.DeptEmp;
import com.Employee.Employee.services.DeptEmpService;

@RestController
@RequestMapping("api/deptemp")
public class DeptEmpController {
    @Autowired
    private DeptEmpService deptEmpService;

    @GetMapping("/employee/{empNo}")
    public ResponseEntity<List<DeptEmp>> getByEmpNo(@PathVariable Integer empNo) {
        List<DeptEmp> result = deptEmpService.findByEmpNo(empNo);
        return result.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(result);
    }

    @GetMapping("/department/{deptNo}")
    public ResponseEntity<List<DeptEmp>> getByDeptNo(@PathVariable String deptNo) {
        List<DeptEmp> result = deptEmpService.findByDeptNo(deptNo);
        return result.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(result);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public DeptEmp create(@RequestBody DeptEmp deptEmp) {
        return deptEmpService.create(deptEmp);
    }

    @DeleteMapping("/{empNo}/{deptNo}/{fromDate}")
    public ResponseEntity<String> delete( @PathVariable Integer empNo, 
    @PathVariable String deptNo, @PathVariable Date fromDate){
        boolean deleted = deptEmpService.delete(empNo, deptNo, fromDate);
        return deleted
            ? ResponseEntity.ok("Deleted successfully")
            : ResponseEntity.notFound().build();
    }
    
}
