package com.amirasadi.pma.dao;

import com.amirasadi.pma.dto.ChartData;
import com.amirasadi.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProjectRepository extends CrudRepository<Project, Long> {
  @Override
  List<Project> findAll();

  @Query(nativeQuery = true, value = "SELECT stage, COUNT(*) as count from PROJECT GROUP BY stage")
  List<ChartData> projectStatus();
}
