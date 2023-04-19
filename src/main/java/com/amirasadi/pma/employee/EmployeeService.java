package com.amirasadi.pma.employee;

import com.amirasadi.pma.shared.dto.EmployeeProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
  @Autowired
  IEmployeeRepository empRepo;

  public Employee save(Employee emp){
    return empRepo.save(emp);
  }

  public List<Employee> findAll(){
    return empRepo.findAll();
  }

  public List<EmployeeProject> employeeProject(){
    return empRepo.employeeProject();
  }
}
