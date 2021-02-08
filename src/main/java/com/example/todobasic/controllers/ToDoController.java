package com.example.todobasic.controllers;

import com.example.todobasic.exceptions.ResourceNotFoundException;
import com.example.todobasic.dto.ToDodto;
import com.example.todobasic.services.ToDoService;
import com.example.todobasic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on February, 2021
 *
 * @author tolga
 */

@Controller
public class ToDoController {

    private ToDoService todoService;

    private UserService userService;

    @Autowired
    public ToDoController(ToDoService toDoService, UserService userService) {
        this.todoService = toDoService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        List<ToDodto> todos = todoService.findToDosByUserId(
                userService.getCurrentUserId().orElseThrow(ResourceNotFoundException::new));
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/todo/{id}")
    public String todoPage(@PathVariable("id") Long id, Model model) {
        ToDodto todoRepr = todoService.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("todo", todoRepr);
        return "todo";
    }

    @GetMapping("/todo/create")
    public String createTodoPage(Model model) {
        model.addAttribute("todo", new ToDodto());
        return "todo";
    }

    @PostMapping("/todo/create")
    public String createTodoPost(@ModelAttribute("todo") ToDodto todo) {
        todoService.save(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/";
    }
}
