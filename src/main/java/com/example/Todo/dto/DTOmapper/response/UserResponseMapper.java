package com.example.Todo.dto.DTOmapper.response;

import com.example.Todo.dto.responseDto.UserResponseDTO;
import com.example.Todo.model.User;

public class UserResponseMapper {
    public static  UserResponseDTO userToDTO(User user){
        UserResponseDTO userResponseDTO =new UserResponseDTO();
        userResponseDTO.setUserName(user.getUserName());

        return userResponseDTO;

    }
    public static  User dTOToUser(UserResponseDTO userDTO){
        User user=new User();
        user.setUserName(userDTO.getUserName());

        return user;

    }
}
