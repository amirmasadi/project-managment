package com.amirasadi.pma.employee;

import com.amirasadi.pma.shared.dto.EmployeeProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService empService;

    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String saveNewEmployee(Employee employee) {
        empService.save(employee);
        return "redirect:/employee";
    }

    @GetMapping
    public String showEmployees(Model model) {
        List<EmployeeProject> employeeList = empService.employeeProject();
        model.addAttribute("employeeList", employeeList);
        return "employees/show-employees";
    }

    @GetMapping("/delete")
    public String deleteEmp(@RequestParam("id") long id){
        empService.deleteEmpById(id);
        return "redirect:/";
    }
    @GetMapping("/update")
    public String updateEmp(@RequestParam("id") long id, Model model){
        Employee theEmp = empService.findByEmployeeId(id);
        model.addAttribute("employee", theEmp);
        return "employees/new-employee";
    }
}
