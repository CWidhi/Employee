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

import com.Employee.Employee.models.DeptManager;
import com.Employee.Employee.services.DeptManagerService;

@RestController
@RequestMapping("/api/deptMan")
public class DeptManagerController {
    @Autowired
    private DeptManagerService deptManagerService;

    @GetMapping("/employee/{empNo}")
    public ResponseEntity<List<DeptManager>> getByEmpNo(@PathVariable Integer empNo){
        List<DeptManager> result = deptManagerService.findByEmpNo(empNo);
        return result.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(result);
    }
    @GetMapping("/department/{deptNo}")
    public ResponseEntity<List<DeptManager>> getByDeptNo(@PathVariable String deptNo){
        List<DeptManager> result = deptManagerService.findByDeptNo(deptNo);
        return result.isEmpty()
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(result);
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public DeptManager create(@RequestBody DeptManager deptManager){
        return deptManagerService.create(deptManager);
    }
    @DeleteMapping("/{empNo}/{deptNo}/{fromDate}")
    public ResponseEntity<String> delete( @PathVariable Integer empNo, 
    @PathVariable String deptNo, @PathVariable Date fromDate) {
        
        boolean deleted = deptManagerService.delete(empNo, deptNo, fromDate);
        return deleted
            ? ResponseEntity.ok("Deleted successfully")
            : ResponseEntity.notFound().build();
    }
}
