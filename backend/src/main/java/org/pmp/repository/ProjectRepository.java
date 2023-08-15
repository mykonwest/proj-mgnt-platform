package org.pmp.repository;

import org.pmp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface ProjectRepository extends JpaRepository<Project, Long> {
}