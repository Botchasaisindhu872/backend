package com.example.Todo.Controller;

import com.example.Todo.Repositories.UserRepository;
import com.example.Todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserRepository userRepo;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> userList=new ArrayList<>();
        userRepo.findAll().forEach(userList::add);
       // userList.addAll(userRepo.findAll());

        return new ResponseEntity<>(userList,HttpStatus.OK);


    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUsersByID(@PathVariable Long id){
            Optional<User> userData = userRepo.findById(id);
            if (userData.isPresent()){
                return new ResponseEntity<>(userData.get(),HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @GetMapping("/search")
    public ResponseEntity<List<User> > findByPrefix(){
        List<User> usersWithSamePrefix = userRepo.findByPrefix();
        return new ResponseEntity<>(usersWithSamePrefix,HttpStatus.OK);


    }
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userObj=userRepo.save(user);

        return new ResponseEntity<>(userObj,HttpStatus.OK);


    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateByID(@PathVariable Long id,@RequestBody User newUserData){
        Optional<User> oldUserData = userRepo.findById(id);
        if (oldUserData.isPresent()){
            User updatedUser=oldUserData.get();
            updatedUser.setUserName(newUserData.getUserName());
            User userObj= userRepo.save(updatedUser);

            //return new ResponseEntity<>(userObj,HttpStatus.OK);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserByID(@PathVariable Long id){

            userRepo.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

    }

}
