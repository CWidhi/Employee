package com.Employee.Employee.models;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@Entity
@Table(name ="employees")
public class Employee {
    @Id
    private Integer empNo;

    @Temporal(TemporalType.DATE)
    @Past(message = "Tanggal lahir harus di masa lalu")
    private Date birthDate;

    @Column(length = 14)
    @NotBlank(message = "Nama depan tidak boleh kosong")
    @Size(max = 14, message = "Nama depan maksimal 14 karakter")
    private String firstName;

    @Column(length = 16)
    @NotBlank(message = "Nama belakang tidak boleh kosong")
    @Size(max = 14, message = "Nama depan maksimal 16 karakter")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Jenis kelamin tidak boleh kosong")
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "Tanggal masuk kerja tidak boleh di masa depan")
    private Date hireDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DeptEmp> deptEmps;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DeptManager> deptManagers;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Title> titles;

    public enum Gender {
        M, F
    }
}
