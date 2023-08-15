package org.pmp.controller;

import org.pmp.model.Project;
import org.pmp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
public class ProjectController {

    @Autowired
    ProjectService service;

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(service.getAllProjects());
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {
        return ResponseEntity.ok(service.getProjectById(id));
    }

    @PostMapping("/project")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(service.createProject(project));
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project project) {
        return ResponseEntity.ok(service.updateProject(id, project));
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") long id) {
        service.deleteProject(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}