package com.amirasadi.pma.project;

import com.amirasadi.pma.employee.Employee;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
  @SequenceGenerator(name = "project_seq", allocationSize = 1)
  private long projectId;
  private String name;
  private String stage;
  private String description;

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
          fetch = FetchType.LAZY)
  @JoinTable(name = "project_employee",
          joinColumns = @JoinColumn(name = "project_id"),
          inverseJoinColumns = @JoinColumn(name = "employee_id"))
  private List<Employee> employees;

  /*we need an empty constructor because we need to pass an empty object from controller to view(thymeleaf)*/
  public Project() {
  }

  public Project(String name, String stage, String description) {
    this.name = name;
    this.stage = stage;
    this.description = description;
  }

  public long getProjectId() {
    return projectId;
  }

  public void setProjectId(long projectId) {
    this.projectId = projectId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  //convenience method
  public void addEmployee(Employee emp) {
    if (employees == null) {
      employees = new ArrayList<>();
    }
    employees.add(emp);
  }
}
