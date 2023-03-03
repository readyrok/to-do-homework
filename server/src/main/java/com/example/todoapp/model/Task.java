package com.example.todoapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public final class Task {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @NotEmpty
    private String category;
    private String name;
    private LocalDate dateCreatedAt;
    private LocalDate dateScheduledAt;
    private LocalDate dateFinishedAt;
    @Min(value = 1)
    private int estimatedHours;
    private int actualHours;

    private boolean isTaskFinished;

    public Task(String category,
                String name,
                LocalDate dateCreatedAt,
                LocalDate dateScheduledAt,
                int estimatedHours,
                boolean isTaskFinished) {
        this.category = category;
        this.name = name;
        this.dateCreatedAt = dateCreatedAt;
        this.dateScheduledAt = dateScheduledAt;
        this.estimatedHours = estimatedHours;
        this.isTaskFinished = isTaskFinished;
    }
}
