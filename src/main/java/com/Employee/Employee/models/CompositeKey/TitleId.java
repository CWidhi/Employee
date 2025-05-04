package com.Employee.Employee.models.CompositeKey;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleId {
    private Integer empNo;
    private String title;
    private Date fromDate;
}
