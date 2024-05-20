package com.todolist.clientApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String password;
    private String username;
}
