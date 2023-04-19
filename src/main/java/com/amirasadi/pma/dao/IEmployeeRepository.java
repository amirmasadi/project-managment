package com.amirasadi.pma.dao;

import com.amirasadi.pma.dto.EmployeeProject;
import com.amirasadi.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  IEmployeeRepository extends CrudRepository<Employee, Long> {
  @Override
  List<Employee> findAll();

  @Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe" +
          ".employee_id) as projectCount " +
          "FROM EMPLOYEE e LEFT JOIN PROJECT_EMPLOYEE pe ON e.employee_id = pe.employee_id GROUP BY e.first_name, e.last_name ORDER BY 3 DESc")
  List<EmployeeProject> employeeProject();
}

