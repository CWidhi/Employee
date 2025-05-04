package com.Employee.Employee.models;

import java.sql.Date;

import com.Employee.Employee.models.CompositeKey.DeptManagerId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "dept_manager")
@IdClass(DeptManagerId.class)
public class DeptManager {
    @Id
    private String deptNo;

    @Id
    private Integer empNo;

    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "deptNo", insertable = false, updatable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "empNo", insertable = false, updatable = false)
    private Employee employee;
}
