package com.Employee.Employee.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class Department {
    
    @Id
    @Column(length = 4)
    private String deptNo;

    @Column(length = 40)
    @NotBlank(message = "Nama department tidak boleh kosong")
    private String deptName;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<DeptEmp> deptEmps;

    @OneToMany(mappedBy = "department")
    private List<DeptManager> deptManagers;
}
