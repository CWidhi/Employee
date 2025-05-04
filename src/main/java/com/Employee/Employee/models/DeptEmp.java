package com.Employee.Employee.models;

import java.sql.Date;

import com.Employee.Employee.models.CompositeKey.DeptEmpId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name= "dept_emp")
@IdClass(DeptEmpId.class)
@Data
public class DeptEmp {
    
    @Id
    private Integer empNo;

    @Id
    private String deptNo;

    @Id
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "empNo", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "deptNo", insertable = false, updatable = false)
    private Department department;
}
