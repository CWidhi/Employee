package com.Employee.Employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.Employee.models.Employee;
import com.Employee.Employee.models.Title;
import com.Employee.Employee.models.CompositeKey.TitleId;
import com.Employee.Employee.repository.EmployeeRepository;
import com.Employee.Employee.repository.TitleRepository;

@Service
public class TitleService {
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Title> getAll(){
        return titleRepository.findAll();
    }

    public Title create(Title title){
        Employee employee = employeeRepository.findById(title.getEmpNo())
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        title.setEmployee(employee);
        return titleRepository.save(title);
        
    }

    public Optional<Title> getById(TitleId id){
        return titleRepository.findById(id);
    }

    public Title update(TitleId id, Title title){
        if (titleRepository.existsById(id)) {
            return titleRepository.save(title);
        }
        throw new RuntimeException("Salary with id " + id + " not found.");
    }
    
    public void delete(TitleId id) {
        titleRepository.deleteById(id);
    }
}
