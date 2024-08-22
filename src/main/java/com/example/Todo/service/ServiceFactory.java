package com.example.Todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {
    private final TaskService taskService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ServiceFactory(TaskService taskService, UserService userService, CategoryService categoryService) {
        this.taskService = taskService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public TaskService getTaskService() {
        return this.taskService;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public CategoryService getCategoryService() {
        return this.categoryService;
    }
}
