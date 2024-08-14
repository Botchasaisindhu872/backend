package com.example.Todo.DTO.DTOmapper.request;

import com.example.Todo.DTO.requestDto.UserRequestDTO;
import com.example.Todo.Model.User;

public class UserRequestMapper {
    public static UserRequestDTO userToDTO(User user){
        UserRequestDTO userRequestDTO =new UserRequestDTO();
        userRequestDTO.setUserName(user.getUserName());

        return userRequestDTO;

    }
    public static  User dTOToUser(UserRequestDTO userDTO){
        User user=new User();
        user.setUserName(userDTO.getUserName());

        return user;

    }
}
