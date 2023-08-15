package org.pmp.repository;

import org.pmp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface TaskRepository extends JpaRepository<Task, Long> {
}