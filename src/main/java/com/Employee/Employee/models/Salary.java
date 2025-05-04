package com.Employee.Employee.models;

import java.sql.Date;

import com.Employee.Employee.models.CompositeKey.SalaryId;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name= "salaries")
@IdClass(SalaryId.class)
public class Salary {
    @Id
    private Integer empNo;

    @Temporal(TemporalType.DATE)
    private Date fromDate;

    private Integer salary;

    @Temporal(TemporalType.DATE)
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "empNo", insertable = false, updatable = false)
    @JsonBackReference
    private Employee employee;
}
