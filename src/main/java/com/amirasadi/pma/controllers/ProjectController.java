package com.amirasadi.pma.controllers;

import com.amirasadi.pma.dao.IEmployeeRepository;
import com.amirasadi.pma.dao.IProjectRepository;
import com.amirasadi.pma.entities.Employee;
import com.amirasadi.pma.entities.Project;
import com.amirasadi.pma.services.EmployeeService;
import com.amirasadi.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
  @Autowired
  ProjectService proService;

  @Autowired
  EmployeeService empService;
  @GetMapping("/new")
  public String showNewProjectForm(Model model){
    Project aProject = new Project();
    model.addAttribute("project", aProject);
    List<Employee> employeesList = empService.findAll();
    model.addAttribute("employeesList", employeesList);
    return "projects/new-project";
  }
  @PostMapping("/save")
  public String createProject(Project project){
    proService.save(project);
    return "redirect:/project";
  }

  @GetMapping
  public String showEmployees(Model model) {
    List<Project> projectsList = proService.findAll();
    model.addAttribute("projectsList", projectsList);
    return "projects/show-projects";
  }
}
