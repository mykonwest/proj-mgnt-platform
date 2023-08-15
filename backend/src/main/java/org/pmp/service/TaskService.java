package org.pmp.service;

import jakarta.persistence.EntityManager;
import org.pmp.model.Task;
import org.pmp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    @Autowired
    EntityManager entityManager;


    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Task createTask(Task project) {
        final long projectId = repository.saveAndFlush(project).getId();
        entityManager.clear();
        return getTaskById(projectId);
    }

    public Task updateTask(long id, Task project) {
        repository.findById(id).ifPresentOrElse(
                p-> {
                    p.setName(p.getName());
                    repository.save(p);
                }
        , () -> System.out.println("Task not found"));
        return repository.findById(id).orElse(null);
    }

    public void deleteTask(long id) {
        repository.deleteById(id);
    }
}
