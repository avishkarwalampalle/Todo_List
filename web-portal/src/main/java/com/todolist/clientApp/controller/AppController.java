package com.todolist.clientApp.controller;

import com.todolist.clientApp.dto.*;
import com.todolist.clientApp.service.WebService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class AppController implements ErrorController {
    private final WebService service;

    @GetMapping("/todo-app")
    public String index(){
        return "user_component/home";
    }

    @GetMapping("/todo-app/profile")
    public String profile(Model model){
        List<Task> tasks = service.accessProfile();
        if(tasks != null) {
            model.addAttribute("tasks", tasks);
            return "task_component/userProfile";
        }
        return "redirect:/todo-app";
    }

    @PostMapping("/todo-app/register")
    public String register(@ModelAttribute RegistrationRequest request) {
        try {
            int statusCode = service.register(request);
            if (statusCode == 201){
                log.info("Registration Done Successfully!");
                return "redirect:/todo-app?registrationSuccess=true";
            }
        } catch (HttpClientErrorException ex) {
            String errorMessage = ex.getResponseBodyAsString();
            log.error("Validation failed: {}", errorMessage);
        }
        return "redirect:/todo-app?registrationSuccess=false";
    }

    @PostMapping("/todo-app/login")
    String login(@ModelAttribute AuthenticationRequest request) {
        try{
            int statusCode = service.login(request);
            switch (statusCode){
                case 202:
                    log.info("User is authenticated!");
                    return "redirect:/todo-app/profile";
                case 401:
                    log.info("Password is incorrect!");
                    break;
                case 404:
                    log.info("user is isn't found!");
                    break;
                case 403:
                    log.info("account isn't active!");
                    break;
            }
        }catch (HttpClientErrorException ex){
            String errorMessage = ex.getResponseBodyAsString();
            log.error("Validation failed: {}", errorMessage);
        }
        return "redirect:/todo-app?loginSuccess=false";
    }

    @PostMapping("/todo-app/reset-password")
    String sendResetMail(@ModelAttribute ResetPasswordRequest request) {
        service.sendResetMail(request.getEmail());
        return "redirect:/todo-app";
    }

    @GetMapping("/todo-app/logout")
    String logout() {
        service.logout();
        return "redirect:/todo-app";
    }

    @PostMapping("/todo-app/profile/addTask")
    String addTask(@ModelAttribute Task task) {
        service.addTask(task);
        return "redirect:/todo-app/profile";
    }

    @PostMapping("/todo-app/profile/updateTask/{id}")
    String updateTask(@PathVariable("id") Integer id,
                      @ModelAttribute Task task) {
       service.updateTask(task);
        return "redirect:/todo-app/profile";
    }

    @GetMapping("/todo-app/profile/deleteTask/{id}")
    String deleteTask(@PathVariable("id") Integer id) {
        service.deleteTask(id);
        return "redirect:/todo-app/profile";
    }

    @GetMapping("/todo-app/profile/filter=personal")
    public String getPersonalTasks(Model model) {
        List<Task> tasks = service.getPersonalTasks();
        model.addAttribute("tasks", tasks);
        return "task_component/userProfile";
    }

    @GetMapping("/todo-app/profile/filter=work")
    public String getWorkTasks(Model model) {
        List<Task> tasks = service.getWorkTasks();
        model.addAttribute("tasks", tasks);
        return "task_component/userProfile";
    }

    @GetMapping("/todo-app/profile/filter=today")
    public String getTodayTasks(Model model) {
        List<Task> tasks = service.getTodayTasks();
        model.addAttribute("tasks", tasks);
        return "task_component/userProfile";
    }

    @GetMapping("/todo-app/profile/filter=next-week")
    public String getWeeklyTasks(Model model) {
        List<Task> tasks = service.getWeeklyTasks();
        model.addAttribute("tasks", tasks);
        return "task_component/userProfile";
    }

    @GetMapping("/todo-app/profile/filter=in-progress")
    public String getInProgressTasks(Model model) {
        List<Task> tasks = service.getInProgressTasks();
        model.addAttribute("tasks", tasks);
        return "task_component/userProfile";
    }

    @GetMapping("/todo-app/profile/filter=completed")
    public String getCompletedTasks(Model model) {
        List<Task> tasks = service.getCompletedTasks();
        model.addAttribute("tasks", tasks);
        return "task_component/userProfile";
    }

    @GetMapping("/todo-app/profile/filter=overdue")
    public String getOverdueTasks(Model model) {
        List<Task> tasks = service.getOverdueTasks();
        model.addAttribute("tasks", tasks);
        return "task_component/userProfile";
    }

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}
