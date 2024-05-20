package com.todolist.clientApp.service;

import com.todolist.clientApp.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebService{
    private final RestTemplate template;
    private final RestTemplateBuilder restTemplateBuilder;
    private static final String BASE_URL = "http://API-GATEWAY";
    private static final String BASE_ENDPOINT = "http://localhost:8080";
    private static final String REGISTER_URL = "/auth/register";
    private static final String LOGIN_URL = "/auth/login";
    private static final String RESET_URL = "/auth/reset-password";
    private static final String PROFILE_URL = "/profile/";
    private static final String UPDATE_URL = "/updateTask/";
    private static final String ADD_URL = "/addTask";
    private static final String DELETE_URL = "/deleteTask/";
    private static final String FILTER_URL = "/filter=";
    public static String jwtToken;
    public static String email;

    public int register(RegistrationRequest request){
        ResponseEntity<String> response = template.postForEntity(BASE_URL+REGISTER_URL, request, String.class);
        return response.getStatusCode().value();
    }

    public int login(AuthenticationRequest request){
        ResponseEntity<String> response = template.postForEntity(BASE_URL+LOGIN_URL, request, String.class);
        jwtToken = response.getBody();
        email = request.getEmail();
        return response.getStatusCode().value();
    }

    private RestTemplate getRestTemplateWithToken() {
        RestTemplate template = restTemplateBuilder.defaultHeader("Authorization", "Bearer " + jwtToken).build();
        return template;
    }

    public List<Task> accessProfile(){
        RestTemplate template = getRestTemplateWithToken();
        return (template != null)?template.getForObject(BASE_ENDPOINT+PROFILE_URL+email, List.class)
                : null;
    }
    public void logout(){
        jwtToken=null;
    }

    public void sendResetMail(String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonBody = "{\"email\":\"" + email +"\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
        template.postForObject(BASE_URL + RESET_URL, requestEntity, String.class);
    }

    public String addTask(Task task){
        RestTemplate template = getRestTemplateWithToken();
        return template.postForObject(BASE_ENDPOINT+PROFILE_URL+email+ADD_URL, task, String.class);
    }

    public String updateTask(Task task){
        RestTemplate template = getRestTemplateWithToken();
        return template.postForObject(BASE_ENDPOINT+PROFILE_URL+email+UPDATE_URL+task.getId(), task, String.class);
    }

    public String deleteTask(Integer id){
        RestTemplate template = getRestTemplateWithToken();
        return template.getForObject(BASE_ENDPOINT+PROFILE_URL+email+DELETE_URL+id, String.class);
    }


    public List<Task> getInProgressTasks() {
        RestTemplate template = getRestTemplateWithToken();
        return template.getForObject(BASE_ENDPOINT+PROFILE_URL+email+FILTER_URL+"in-progress", List.class);

    }

    public List<Task> getCompletedTasks() {
        RestTemplate template = getRestTemplateWithToken();
        return template.getForObject(BASE_ENDPOINT+PROFILE_URL+email+FILTER_URL+"completed", List.class);

    }

    public List<Task> getOverdueTasks() {
        RestTemplate template = getRestTemplateWithToken();
        return template.getForObject(BASE_ENDPOINT+PROFILE_URL+email+FILTER_URL+"overdue", List.class);

    }

    public List<Task> getPersonalTasks() {
        RestTemplate template = getRestTemplateWithToken();
        return template.getForObject(BASE_ENDPOINT+PROFILE_URL+email+FILTER_URL+"personal", List.class);

    }

    public List<Task> getWorkTasks() {
        RestTemplate template = getRestTemplateWithToken();
        return template.getForObject(BASE_ENDPOINT+PROFILE_URL+email+FILTER_URL+"work", List.class);

    }

    public List<Task> getTodayTasks() {
        RestTemplate template = getRestTemplateWithToken();
        return template.getForObject(BASE_ENDPOINT+PROFILE_URL+email+FILTER_URL+"today", List.class);

    }

    public List<Task> getWeeklyTasks() {
        RestTemplate template = getRestTemplateWithToken();
        return template.getForObject(BASE_ENDPOINT+PROFILE_URL+email+FILTER_URL+"next-week", List.class);
    }

}
