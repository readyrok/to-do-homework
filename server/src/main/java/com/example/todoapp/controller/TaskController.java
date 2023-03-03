package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity addTask(@RequestParam("category") String category,
                                  @RequestParam("name") String name,
                                  @RequestParam("dateScheduledAt") String dateScheduledAt,
                                  @RequestParam("estimatedHours") int estimatedHours){
        Task task = taskService.addTask(category, name, dateScheduledAt, estimatedHours);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") String id){
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity finishTask(@RequestParam("id") String id, @RequestParam("actualHours") int actualHours){
        taskService.finishTask(id, actualHours);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/asc")
    public List<Task> getTasksOrderedByScheduledDateAsc(){
        return taskService.getTasksOrderedByScheduledDateAsc();
    }

    @GetMapping("/desc")
    public List<Task> getTasksOrderedByScheduledDateDesc(){
        return taskService.getTasksOrderedByScheduledDateDesc();
    }
}






