package com.example.Todo.service;

import com.example.Todo.Repositories.read.UserReadRepository;
import com.example.Todo.Repositories.write.UserWriteRepository;
import com.example.Todo.model.Category;
import com.example.Todo.model.Task;
import com.example.Todo.model.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserWriteRepository userWriteRepo;
    @Autowired
    private UserReadRepository userReadRepo;
    public User addUser(User user) {
                User addedUser= CategoryService.addCategories(user);
                return  userWriteRepo.save(addedUser);
    }


   /* public User updateUserByID(Long id,User newUser) {
        Optional<User> oldUserOpt= userReadRepo.findById(id);

        if(oldUserOpt.isPresent()){
            User oldUser=oldUserOpt.get();
            oldUser.setUserName(newUser.getUserName());
            oldUser=CategoryService.updateCategory(oldUser,newUser);
            oldUser=TaskService.updateTask(oldUser,newUser);





          return  userWriteRepo.save(oldUser);
        }
        return new User();

    }*/

}
