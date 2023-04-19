package com.amirasadi.pma.services;

import com.amirasadi.pma.dao.IProjectRepository;
import com.amirasadi.pma.dto.ChartData;
import com.amirasadi.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

  @Autowired
  IProjectRepository proRepo;

  public Project save(Project pro){
    return proRepo.save(pro);
  }

  public List<Project> findAll(){
    return proRepo.findAll();
  }

  public List<ChartData> projectStatus(){
    return proRepo.projectStatus();
  }
}
