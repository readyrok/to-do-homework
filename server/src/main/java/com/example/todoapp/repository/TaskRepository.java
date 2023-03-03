package com.example.todoapp.repository;

import com.example.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findAllByOrderByDateScheduledAtAsc();
    List<Task> findAllByOrderByDateScheduledAtDesc();
}
