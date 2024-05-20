package com.todolist.clientApp.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Task {
    private String userEmail;
    private Integer id;
    private String title;
    private String description;
    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;
    private String status;
}

