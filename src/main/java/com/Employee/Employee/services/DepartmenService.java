package com.Employee.Employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Employee.Employee.models.Department;
import com.Employee.Employee.models.DTO.DepartmentDTO;
import com.Employee.Employee.repository.DepartmenRepository;

import jakarta.validation.Valid;

@Service
public class DepartmenService {
    
    @Autowired
    private DepartmenRepository departmenRepo;

    public List<Department> getAllDepartmen(){
        return departmenRepo.findAll();
    }

    public Department getById(String deptNo){
        return departmenRepo
            .findById(deptNo)
            .orElseThrow(()
                        -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Department dengan ID " + deptNo + " tidak ditemukan"
                            )
                );
    }

    public Department create(@Valid Department department){
        if (departmenRepo.existsById(department.getDeptNo())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Department dengan ID " + department.getDeptNo() + " sudah ada");
        }
        if (departmenRepo.existsByDeptName(department.getDeptName())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Department dengan nama '" + department.getDeptName() + "' sudah ada");
        }

        return departmenRepo.save(department);
    }

     public Department update(String deptNo, @Valid Department department){
        Department existingDepartment =  departmenRepo
        .findById(deptNo)
        .orElseThrow(()
                -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Department dengan ID " + deptNo + " tidak ditemukan"
                    )
        );

        existingDepartment.setDeptName(department.getDeptNo());
        existingDepartment.setDeptName(department.getDeptName());

        return departmenRepo.save(existingDepartment);
    }

    public Department updatePartial(String deptNo, @Valid DepartmentDTO department) {
        Department existingDepartment = departmenRepo
                .findById(deptNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department dengan ID " + deptNo + " tidak ditemukan"));

        if (department.getDeptName() != null) {
            existingDepartment.setDeptName(department.getDeptName());
        }
        return departmenRepo.save(existingDepartment);
    }

    public void delete(String deptNo){
        Department existingDepartment = departmenRepo.findById(deptNo)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Department dengan ID " + deptNo + " tidak ditemukan"
        ));

        departmenRepo.delete(existingDepartment); 
    }
}
