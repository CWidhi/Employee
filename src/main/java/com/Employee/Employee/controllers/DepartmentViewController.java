package com.Employee.Employee.controllers;

import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Employee.Employee.models.Department;
// import com.Employee.Employee.models.DeptEmp;
// import com.Employee.Employee.models.Employee;
import com.Employee.Employee.services.DepartmenService;
// import com.Employee.Employee.services.DeptEmpService;
import com.Employee.Employee.services.EmployeeService;

@Controller
@RequestMapping("/department")
public class DepartmentViewController {
    @Autowired
    private DepartmenService departmenService;
    @Autowired 
    private EmployeeService employeeService;
    // @Autowired
    // private DeptEmpService deptEmpService;


    @GetMapping("/")
    public String showAllDepartment(Model model){
        List<Department> departments = departmenService.getAllDepartmen();
        model.addAttribute("departments", departments);
        return "department/list";
    }
    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("department", new Department());
        return "department/create";
    }
    @GetMapping("/{deptNo}")
    public String showDepartmentDetail(@PathVariable String deptNo, Model model) {
        Department department = departmenService.getById(deptNo);
        model.addAttribute("department", department);
        return "department/detail";
    }
    @GetMapping("/edit/{deptNo}")
    public String showEditPage(@PathVariable String deptNo, Model model) {
        Department department = departmenService.getById(deptNo);
        model.addAttribute("department", department);
        return "department/edit"; 
    }

    @GetMapping("/add-member/{deptNo}")
    // public String showAddMemberForm(@PathVariable String deptNo, Model model) {
    //     List<Employee> allEmployees = employeeService.getAllEmployee();
    //     List<DeptEmp> existingMembers = deptEmpService.findByDeptNo(deptNo); 
    //     Set<Integer> existingEmpNos = existingMembers.stream()
    //         .map(DeptEmp::getEmpNo)
    //         .collect(Collectors.toSet());

    //     List<Employee> availableEmployees = allEmployees.stream()
    //         .filter(emp -> !existingEmpNos.contains(emp.getEmpNo()))
    //         .collect(Collectors.toList());
    //     model.addAttribute("employees", availableEmployees);
    //     model.addAttribute("deptNo", deptNo);
    //     return "department/member/create";
    // }

    public String showAddMemberForm(@PathVariable String deptNo, Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        model.addAttribute("deptNo", deptNo);
        return "department/member/create";
    }

    @GetMapping("/add-manajer/{deptNo}")
    public String ShowAddManajerForm(@PathVariable String deptNo, Model model){
        model.addAttribute("employees", employeeService.getAllEmployee());
        model.addAttribute("deptNo", deptNo);
        return "department/manajer/create";
    }
}
