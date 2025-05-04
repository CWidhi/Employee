package com.Employee.Employee.models;

import java.sql.Date;

import com.Employee.Employee.models.CompositeKey.TitleId;
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

@Entity
@Table(name = "titles")
@IdClass(TitleId.class)
@Data
public class Title {
    @Id
    private Integer empNo;

    @Id
    private String title;

    @Id
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Temporal(TemporalType.DATE)
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "empNo", insertable = false, updatable = false)
    @JsonBackReference
    private Employee employee;
}
