package com.example.todoapp.service;

import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task addTask(String category, String name, String dateScheduled, int estimatedHours) {
        if(category.length()==0){
            throw new IllegalArgumentException("Category cannot be empty.");
        }

        if(name.length()==0){
            throw new IllegalArgumentException("Task name cannot be empty.");
        }

        if(dateScheduled.isEmpty()){
            throw new IllegalArgumentException("Scheduled date cannot be empty.");
        }

        if(estimatedHours<0){
            throw new IllegalArgumentException("Estimated hours can't be lower than zero.");
        }

        LocalDate dateCreatedAt = LocalDate.now();
        LocalDate dateScheduledAt = LocalDate.parse(dateScheduled);

        if(dateCreatedAt.isAfter(dateScheduledAt)){
            throw new IllegalArgumentException("Date scheduled cannot be before today.");
        }

        Task taskToAdd = new Task(category, name, dateCreatedAt, dateScheduledAt, estimatedHours, false);

        return taskRepository.save(taskToAdd);
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(String id) {
        if(id.length()==0){
            throw new IllegalArgumentException("ID cannot be empty.");
        }

        Optional<Task> taskToDelete = taskRepository.findById(id);

        if(taskToDelete.isEmpty()){
            throw new EntityNotFoundException("Task not found");
        }

        if(taskToDelete.get().isTaskFinished()){
            throw new EntityNotFoundException("Can't delete a finished task");
        }

        taskRepository.deleteById(id);
    }

    public void finishTask(String id, int actualHours) {
        if(id.length()==0){
            throw new IllegalArgumentException("ID cannoy be empty.");
        }

        if(actualHours<=0){
            throw new IllegalArgumentException("Actual hours the task took cannot be lower or equal to zero.");
        }

        Optional<Task> taskToFinish = taskRepository.findById(id);

        if(taskToFinish.isEmpty()){
            throw new EntityNotFoundException("Task does not exist");
        }

        Task task = taskToFinish.get();

        task.setTaskFinished(true);
        task.setActualHours(actualHours);
        task.setDateFinishedAt(LocalDate.now());

        taskRepository.save(task);
    }

    public List<Task> getTasksOrderedByScheduledDateAsc(){
        return taskRepository.findAllByOrderByDateScheduledAtAsc();
    }

    public List<Task> getTasksOrderedByScheduledDateDesc(){
        return taskRepository.findAllByOrderByDateScheduledAtDesc();
    }
}


