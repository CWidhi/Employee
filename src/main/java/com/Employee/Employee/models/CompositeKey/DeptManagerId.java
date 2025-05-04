package com.Employee.Employee.models.CompositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptManagerId {
    private Integer empNo;
    private String deptNo;
}
