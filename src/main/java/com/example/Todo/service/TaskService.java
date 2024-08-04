package com.example.Todo.service;

import com.example.Todo.Repositories.read.UserReadRepository;
import com.example.Todo.Repositories.write.UserWriteRepository;
import com.example.Todo.model.Category;
import com.example.Todo.model.Task;
import com.example.Todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private UserWriteRepository userWriteRepo;
    @Autowired
    private UserReadRepository userReadRepo;
    public static void addTasks(Category category){
        for (Task task : category.getSubTasks()) {
                if (task.getCategory() == null) {
                    task.setCategory(category);
                }
                if (task.getParentTask() == null) {
                    task.setParentTask(task);
                }
                TaskService.addSubTasks(task,category);
            }

    }
    public static void addSubTasks(Task task, Category category){
        for (Task subTask : task.getSubTasks()) {
                if (subTask.getCategory() == null) {
                    subTask.setCategory(category);
                }
                if (subTask.getParentTask() == null) {
                    subTask.setParentTask(task);
                }
        }
    }
}
