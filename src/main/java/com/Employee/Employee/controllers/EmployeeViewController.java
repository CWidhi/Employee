package com.Employee.Employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Employee.Employee.models.Employee;
import com.Employee.Employee.services.EmployeeService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class EmployeeViewController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String showAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployee();
        model.addAttribute("employees", employees);
        return "employee/list"; 
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }

    @GetMapping("/employees/edit/{empNo}")
    public String showEditPage(@PathVariable Integer empNo, Model model) {
        Employee employee = employeeService.getById(empNo);
        model.addAttribute("employee", employee);
        return "employee/edit"; 
    }

    @GetMapping("/employee/{empNo}")
    public String getEmployeeDetail(@PathVariable Integer empNo, Model model) {
        Employee employee = employeeService.getById(empNo);
        model.addAttribute("employee", employee);
        return "employee/detail";  
    }
}
