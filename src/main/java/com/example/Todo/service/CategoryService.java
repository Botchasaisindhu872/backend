package com.example.Todo.service;

import com.example.Todo.Repositories.read.UserReadRepository;
import com.example.Todo.Repositories.write.UserWriteRepository;
import com.example.Todo.model.Category;
import com.example.Todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private UserWriteRepository userWriteRepo;
    @Autowired
    private UserReadRepository userReadRepo;
    public static User addCategories(User user){
        for (Category category : user.getCategories()) {
            if (category.getUser() == null) {
                category.setUser(user);
            }
            TaskService.addTasks(category);
        }
        return user;
    }
    /*
    public User  updateCategory (User oldUser, User newUser){



    }
    */

}
