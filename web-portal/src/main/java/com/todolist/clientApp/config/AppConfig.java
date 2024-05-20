package com.todolist.clientApp.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced
    public RestTemplate template(){
        return new RestTemplate();
    }
}