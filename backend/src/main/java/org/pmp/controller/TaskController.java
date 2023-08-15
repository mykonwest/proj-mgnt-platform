package org.pmp.controller;

import org.pmp.model.Task;
import org.pmp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") long id) {
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(service.createTask(task));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
        return ResponseEntity.ok(service.updateTask(id, task));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        service.deleteTask(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}