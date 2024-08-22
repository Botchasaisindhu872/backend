package com.example.Todo.dto.DTOmapper.request;

import com.example.Todo.dto.requestDto.UserRequestDTO;
import com.example.Todo.model.User;

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
