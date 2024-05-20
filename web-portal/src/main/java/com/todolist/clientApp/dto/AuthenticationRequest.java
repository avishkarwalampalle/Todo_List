package com.todolist.clientApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
