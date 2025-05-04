package com.Employee.Employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Employee.Employee.models.Title;
import com.Employee.Employee.services.EmployeeService;
import com.Employee.Employee.services.TitleService;

@Controller
@RequestMapping("/title")
public class TitleViewController {
    @Autowired
    private TitleService titleService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String showAllTitle(Model model) {
        List<Title> title = titleService.getAll();
        model.addAttribute("titels", title);
        return "title/list"; 
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "title/create";
    }

    @GetMapping("detail/{empNo}/{title}/{fromDate}")
    public String showDetailForm(@PathVariable Integer empNo, @PathVariable String title, @PathVariable String fromDate) {
        return "title/detail"; 
    }

    @GetMapping("edit/{empNo}/{title}/{fromDate}")
    public String showEditForm(@PathVariable Integer empNo, @PathVariable String title, @PathVariable String fromDate) {
        return "title/edit"; 
    }
}
