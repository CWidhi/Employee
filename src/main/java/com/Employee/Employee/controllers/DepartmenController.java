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

import com.Employee.Employee.models.Department;
import com.Employee.Employee.models.DTO.DepartmentDTO;
import com.Employee.Employee.services.DepartmenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/departmen")
public class DepartmenController {
    
    @Autowired
    private DepartmenService departmenService;

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        List<Department> departmens = departmenService.getAllDepartmen();
        return ResponseEntity.ok(departmens);
    }

    @GetMapping("/{deptNo}")
    public Department getById(@PathVariable String deptNo){
        return departmenService.getById(deptNo);
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody @Valid Department department){
        Department created = departmenService.create(department);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{deptNo}")
    public ResponseEntity<Department> update(@PathVariable String deptNo, @RequestBody @Valid Department department){
        Department update = departmenService.update(deptNo, department);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @PatchMapping("/{deptNo}")
    public ResponseEntity<Department> updatePartial(@PathVariable String deptNo, @RequestBody DepartmentDTO dto) {
        Department updatedDepartment = departmenService.updatePartial(deptNo, dto);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{deptNo}")
    public ResponseEntity<String> delete(@PathVariable String deptNo){
        departmenService.delete(deptNo);
        return ResponseEntity.ok("Delete successfully");
    }

}
