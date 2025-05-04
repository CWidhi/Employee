package com.Employee.Employee.models.CompositeKey;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryId {
    private Integer empNo;
    private Date fromDate;
}
