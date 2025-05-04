package com.Employee.Employee.models.DTO;

import java.sql.Date;

import com.Employee.Employee.models.Employee.Gender;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private Date hireDate;
}
