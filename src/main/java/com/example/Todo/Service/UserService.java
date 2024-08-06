package com.example.Todo.Service;

import com.example.Todo.DTOmapper.request.UserRequestMapper;
import com.example.Todo.DTOmapper.response.UserResponseMapper;
import com.example.Todo.DTO.requestDto.UserRequestDTO;
import com.example.Todo.DTO.responseDto.UserResponseDTO;
import com.example.Todo.Repositories.read.UserReadRepository;
import com.example.Todo.Repositories.write.UserWriteRepository;
import com.example.Todo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserWriteRepository userWriteRepo;
    @Autowired
    private  UserReadRepository userReadRepo;


    public UserResponseDTO addUser(UserRequestDTO userDTO) {
            User user = UserRequestMapper.dTOToUser(userDTO);
            userWriteRepo.save(user);


            return UserResponseMapper.userToDTO(user);
    }


   public UserResponseDTO updateUserByID(Long id,UserRequestDTO newUser) {
        Optional<User> oldUserOpt= userReadRepo.findById(id);

        if(oldUserOpt.isPresent()){
            User oldUser=oldUserOpt.get();
            oldUser.setUserName(newUser.getUserName());
            userWriteRepo.save(oldUser);
            return UserResponseMapper.userToDTO(oldUser);
        }
        return null;

    }
    public  List<UserResponseDTO> getAllUsers(){
        List<User> userList=userReadRepo.findAll();
        List<UserResponseDTO> userDTOList =new ArrayList<>();
        for (User user:userList){
            userDTOList.add(UserResponseMapper.userToDTO(user));
        }
        return userDTOList;
    }
    public  UserResponseDTO getUserByID(Long id){
        Optional<User> optUserObject=userReadRepo.findById(id);

        if(optUserObject.isPresent()) {
            User user = optUserObject.get();
            UserResponseDTO userDTO=UserResponseMapper.userToDTO(user);
            return userDTO;
        }
        return new UserResponseDTO();
    }

    public  UserResponseDTO deleteUser(Long id){
        Optional<User> optUserObject=userReadRepo.findById(id);

        if(optUserObject.isPresent()) {
            User user = optUserObject.get();
            userWriteRepo.deleteById(id);
            return UserResponseMapper.userToDTO(user);
        }
        return new UserResponseDTO();
    }

}
