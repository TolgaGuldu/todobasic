package com.example.todobasic.dto;

import com.example.todobasic.entity.ToDo;
import com.example.todobasic.entity.status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created on February, 2021
 *
 * @author tolga
 */

public class ToDodto {

    private long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    private String username;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private LocalDateTime createdDate;

    private status status;

    public ToDodto() {
    }

    public ToDodto(ToDo toDo) {
        this.id = toDo.getId();
        this.title=toDo.getTitle();
        this.description = toDo.getDescription();
        this.username = toDo.getUser().getUsername();
        this.targetDate = toDo.getTargetDate();
        this.createdDate=toDo.getCreatedDate();
        this.status= toDo.getStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }
}
