package org.example.TrabalhoChoma.repository;

import org.example.TrabalhoChoma.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
