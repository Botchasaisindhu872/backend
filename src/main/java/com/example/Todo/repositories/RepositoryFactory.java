package com.example.Todo.repositories;

import com.example.Todo.repositories.read.CategoryReadRepository;
import com.example.Todo.repositories.read.TaskReadRepository;
import com.example.Todo.repositories.read.UserReadRepository;
import com.example.Todo.repositories.write.CategoryWriteRepository;
import com.example.Todo.repositories.write.TaskWriteRepository;
import com.example.Todo.repositories.write.UserWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory {
    private final TaskReadRepository taskReadRepo;


    private final TaskWriteRepository taskWriteRepo;

    private final UserWriteRepository userWriteRepo;

    private final UserReadRepository userReadRepo;

    private final CategoryWriteRepository categoryWriteRepo;


    private final CategoryReadRepository categoryReadRepo;
    @Autowired
    public RepositoryFactory(TaskReadRepository taskReadRepo, TaskWriteRepository taskWriteRepo, UserWriteRepository userWriteRepo, UserReadRepository userReadRepo, CategoryWriteRepository categoryWriteRepo, CategoryReadRepository categoryReadRepo) {
        this.taskReadRepo = taskReadRepo;
        this.taskWriteRepo = taskWriteRepo;
        this.userWriteRepo = userWriteRepo;
        this.userReadRepo = userReadRepo;
        this.categoryWriteRepo = categoryWriteRepo;
        this.categoryReadRepo = categoryReadRepo;
    }

    public TaskReadRepository getTaskReadRepo() {
        return this.taskReadRepo;
    }

    public TaskWriteRepository getTaskWriteRepo() {
        return this.taskWriteRepo;
    }

    public UserWriteRepository getUserWriteRepo() {
        return this.userWriteRepo;
    }

    public UserReadRepository getUserReadRepo() {
        return this.userReadRepo;
    }

    public CategoryWriteRepository getCategoryWriteRepo() {
        return this.categoryWriteRepo;
    }

    public CategoryReadRepository getCategoryReadRepo() {
        return this.categoryReadRepo;
    }
}
