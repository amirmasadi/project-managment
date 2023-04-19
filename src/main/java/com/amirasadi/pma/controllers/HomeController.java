package com.amirasadi.pma.controllers;

import com.amirasadi.pma.dao.IEmployeeRepository;
import com.amirasadi.pma.dao.IProjectRepository;
import com.amirasadi.pma.dto.ChartData;
import com.amirasadi.pma.dto.EmployeeProject;
import com.amirasadi.pma.entities.Employee;
import com.amirasadi.pma.entities.Project;
import com.amirasadi.pma.services.EmployeeService;
import com.amirasadi.pma.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProjectService proService;

    @Autowired
    EmployeeService empService;

    @GetMapping("/")
    public String home(Model model) throws JsonProcessingException {
        List<ChartData> projectStatus = proService.projectStatus();
        ObjectMapper objectMapper = new ObjectMapper();
        String projectStatusStr = objectMapper.writeValueAsString(projectStatus);
        model.addAttribute("projectStatus", projectStatusStr);

        List<Project> projectsList = proService.findAll();
        List<EmployeeProject> employeeList = empService.employeeProject();
        model.addAttribute("projectsList", projectsList);
        model.addAttribute("employeeList", employeeList);
        return "main/home";
    }
}
