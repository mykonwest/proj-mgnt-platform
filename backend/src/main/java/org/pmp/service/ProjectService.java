package org.pmp.service;

import jakarta.persistence.EntityManager;
import org.pmp.model.Project;
import org.pmp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class ProjectService {

    @Autowired
    ProjectRepository repository;

    @Autowired
    EntityManager entityManager;


    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Project getProjectById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Project createProject(Project project) {
        final long projectId = repository.saveAndFlush(project).getId();
        entityManager.clear();
        return getProjectById(projectId);
    }

    public Project updateProject(long id, Project project) {
        repository.findById(id).ifPresentOrElse(
                p-> {
                    p.setName(p.getName());
                    p.setClient(project.getClient());
                    repository.save(p);
                }
        , () -> System.out.println("Project not found"));
        return repository.findById(id).orElse(null);
    }

    public void deleteProject(long id) {
        repository.deleteById(id);
    }
}
