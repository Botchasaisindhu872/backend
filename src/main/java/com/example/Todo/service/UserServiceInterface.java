package com.example.Todo.service;

import com.example.Todo.dto.requestDto.UserRequestDTO;
import com.example.Todo.dto.responseDto.UserResponseDTO;

import java.util.List;

public interface UserServiceInterface {

    public UserResponseDTO addUser(UserRequestDTO userDTO);
    public UserResponseDTO updateUserByID(Long id, UserRequestDTO newUser);
    public List<UserResponseDTO> getAllUsers();
    public UserResponseDTO getUserByID(Long id);
    public UserResponseDTO deleteUser(Long id);

}
