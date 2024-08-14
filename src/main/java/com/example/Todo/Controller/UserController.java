package com.example.Todo.Controller;

import com.example.Todo.DTO.requestDto.UserRequestDTO;
import com.example.Todo.DTO.responseDto.UserResponseDTO;
import com.example.Todo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;
    //Get Mappings

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){

        List<UserResponseDTO> userDTOList = userService.getAllUsers();
        if(userDTOList.isEmpty()){
         return new ResponseEntity<>(userDTOList,HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(userDTOList, HttpStatus.OK);


    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUsersByID(@PathVariable Long id){
            UserResponseDTO userDTO = userService.getUserByID(id);

            return  new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    //Post Mappings
    @PostMapping
    public ResponseEntity<UserResponseDTO >addUser(@RequestBody UserRequestDTO userDTO){
        UserResponseDTO userResponseDTO = userService.addUser(userDTO);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);



    }

    //put mappings
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateByID(@PathVariable Long id,@RequestBody UserRequestDTO newUser){
        UserResponseDTO userDTO= userService.updateUserByID(id,newUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }




    //delete Mappings
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserByID(@PathVariable Long id){

        UserResponseDTO userDTO=userService.deleteUser(id);
        return new ResponseEntity<>( userDTO, HttpStatus.OK);

    }

}
