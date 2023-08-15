package org.pmp.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pmp.model.Task;
import org.pmp.repository.TaskRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    @Resource
    TaskService service;

    Task task;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setName("Make Unit Tests");
        task.setId(1L);

    }

    @Test
    public void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    void getAllTasks() {
        final List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(repository.findAll()).thenReturn(tasks);
        assertThat(service.getAllTasks()).isEqualTo(tasks);
    }

    @Test
    void getTaskById() {
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        assertThat(service.getTaskById(1L)).isEqualTo(task);
    }

    @Test
    void createTask() {
        when(repository.saveAndFlush(task)).thenReturn(task);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        assertThat(service.createTask(task)).isEqualTo(task);
    }

    @Test
    @SuppressWarnings("unchecked")
    void updateTask() {
        Task updatedTask = new Task();
        updatedTask.setName("Refactor Code");
        updatedTask.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(task), Optional.of(updatedTask));
        assertThat(service.getTaskById(1L)).isEqualTo(task);
        when(repository.save(updatedTask)).thenReturn(updatedTask);
        assertThat(service.updateTask(1L, updatedTask)).isEqualTo(updatedTask);
        assertThat(service.getTaskById(1L)).isEqualTo(updatedTask);
    }

    @Test
    @SuppressWarnings("unchecked")
    void deleteTask() {
        when(repository.findById(1L)).thenReturn(Optional.of(task), Optional.empty());
        assertThat(service.getTaskById(1L)).isEqualTo(task);
        assertDoesNotThrow(() -> service.deleteTask(1L));
        assertThat(service.getTaskById(1L)).isNull();
    }
}