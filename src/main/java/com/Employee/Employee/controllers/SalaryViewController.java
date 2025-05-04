package com.Employee.Employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Employee.Employee.models.Salary;
import com.Employee.Employee.services.EmployeeService;
import com.Employee.Employee.services.SalaryService;

@Controller
@RequestMapping("/salaries")
public class SalaryViewController {
    @Autowired
    private SalaryService salaryService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String showAllSalary(Model model) {
        List<Salary> salary = salaryService.getAll();
        model.addAttribute("salaries", salary);
        return "salary/list"; 
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "salary/create";
    }
    
    @GetMapping("detail/{empNo}/{fromDate}")
    public String showSalaryDetailForm(@PathVariable Integer empNo, @PathVariable String fromDate) {
        return "salary/detail"; 
    }
    
    @GetMapping("edit/{empNo}/{fromDate}")
    public String showSalaryEditForm(@PathVariable Integer empNo, @PathVariable String fromDate) {
        return "salary/edit"; 
    }
}
