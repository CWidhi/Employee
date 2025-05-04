package com.Employee.Employee.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Employee.models.Title;
import com.Employee.Employee.models.CompositeKey.TitleId;
import com.Employee.Employee.services.TitleService;

@RestController
@RequestMapping("/api/title")
public class TitleController {
    @Autowired
    private TitleService titleService;

    @GetMapping
    public List<Title> getAll() {
        return titleService.getAll();
    }
    @PostMapping
    public Title create(@RequestBody Title title) {
        return titleService.create(title);
    }

    @GetMapping("/{empNo}/{title}/{fromDate}")
    public ResponseEntity<?> getById(@PathVariable Integer empNo, @PathVariable String title, @PathVariable String fromDate) {
        TitleId id = new TitleId(empNo, title, java.sql.Date.valueOf(fromDate));
        Optional<Title> titleOpt = titleService.getById(id);

        if (titleOpt.isPresent()) {
            Title titleData = titleOpt.get();
            Map<String, Object> response = new HashMap<>();
            response.put("title", titleData.getTitle());
            response.put("fromDate", titleData.getFromDate());
            response.put("toDate", titleData.getToDate());

            Map<String, Object> employeeData = new HashMap<>();
            employeeData.put("firstName", titleData.getEmployee().getFirstName());
            employeeData.put("lastName", titleData.getEmployee().getLastName());

            response.put("employee", employeeData);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Title not found");
        }
    }

    @PutMapping("/{empNo}/{title}/{fromDate}")
    public Title update(@PathVariable Integer empNo,  @PathVariable String title, @PathVariable String fromDate, @RequestBody Title titel) {
        TitleId id = new TitleId(empNo, title, java.sql.Date.valueOf(fromDate));
        return titleService.update(id, titel);
    }

    @DeleteMapping("/{empNo}/{title}/{fromDate}")
    public void delete(@PathVariable Integer empNo, @PathVariable String title, @PathVariable String fromDate) {
        TitleId id = new TitleId(empNo, title, java.sql.Date.valueOf(fromDate));
        titleService.delete(id);
    }

}
