package com.microservices.exception;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<?> errors;
}