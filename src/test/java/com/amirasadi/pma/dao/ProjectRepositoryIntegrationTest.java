package com.amirasadi.pma.dao;

import com.amirasadi.pma.entities.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath" +
        ":data.sql"}), @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:drop.sql"})})
public class ProjectRepositoryIntegrationTest {
  @Autowired
  IProjectRepository proRepo;

  @Test
  public void ifNewProjectSaved_thenSuccess(){
    Project newProject = new Project("new project from test", "COMPELETE", "test description");
    proRepo.save(newProject);
    assertEquals(9, proRepo.findAll().size());
  }
}
