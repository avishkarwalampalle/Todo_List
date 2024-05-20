package com.todolist.clientApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    private String email;
}
