package com.Employee.Employee.models.DTO;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalaryDTO {
    @NotNull
    private Integer empNo;
    @NotNull
    private Date fromDate;
    @NotNull
    private Integer salary;
    private Date toDate;
}