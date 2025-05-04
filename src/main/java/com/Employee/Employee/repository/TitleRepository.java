package com.Employee.Employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Employee.models.Title;
import com.Employee.Employee.models.CompositeKey.TitleId;

public interface TitleRepository extends JpaRepository<Title, TitleId>{
    List<Title> findByEmpNo(Integer empNo);
}
