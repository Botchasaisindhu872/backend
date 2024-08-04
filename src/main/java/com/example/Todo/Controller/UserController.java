package com.example.Todo.Controller;

import com.example.Todo.model.User;
import com.example.Todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;
    //Get Mappings
    /*
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){




    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUsersByID(@PathVariable Long id){


    }*/

    //Post Mappings
    @PostMapping
    public User addUser(@RequestBody User user){
           return userService.addUser(user);

    }
/*
    //put mappings
    @PutMapping("/{id}")
    public ResponseEntity<User> updateByID(@PathVariable Long id,@RequestBody User newUser){
        User user= userService.updateUserByID(id,newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }



    }
    //delete Mappings
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserByID(@PathVariable Long id){



    }
*/
}
