package org.pmp.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pmp.model.Project;
import org.pmp.repository.ProjectRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectServiceTest {

    @Mock
    ProjectRepository repository;

    @InjectMocks
    @Resource
    ProjectService service;

    Project project;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        project = new Project();
        project.setName("Project X");
        project.setId(1L);

    }

    @Test
    public void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    void getAllProjects() {
        final List<Project> projects = new ArrayList<>();
        projects.add(project);
        when(repository.findAll()).thenReturn(projects);
        assertThat(service.getAllProjects()).isEqualTo(projects);
    }

    @Test
    void getProjectById() {
        when(repository.findById(1L)).thenReturn(Optional.of(project));
        assertThat(service.getProjectById(1L)).isEqualTo(project);
    }

    @Test
    void createProject() {
        when(repository.saveAndFlush(project)).thenReturn(project);
        when(repository.findById(1L)).thenReturn(Optional.of(project));
        assertThat(service.createProject(project)).isEqualTo(project);
    }

    @Test
    @SuppressWarnings("unchecked")
    void updateProject() {
        Project updatedProject = new Project();
        updatedProject.setName("Project Y");
        updatedProject.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(project), Optional.of(updatedProject));
        assertThat(service.getProjectById(1L)).isEqualTo(project);
        when(repository.save(updatedProject)).thenReturn(updatedProject);
        assertThat(service.updateProject(1L, updatedProject)).isEqualTo(updatedProject);
        assertThat(service.getProjectById(1L)).isEqualTo(updatedProject);
    }

    @Test
    @SuppressWarnings("unchecked")
    void deleteProject() {
        when(repository.findById(1L)).thenReturn(Optional.of(project), Optional.empty());
        assertThat(service.getProjectById(1L)).isEqualTo(project);
        assertDoesNotThrow(() -> service.deleteProject(1L));
        assertThat(service.getProjectById(1L)).isNull();
    }
}