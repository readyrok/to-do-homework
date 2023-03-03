package com.example.todoapp.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String id) {
        super(String.format("Task with Id %d not found", id));
    }
}
