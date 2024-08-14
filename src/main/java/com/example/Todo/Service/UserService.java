package com.example.Todo.Service;

import com.example.Todo.DTO.DTOmapper.request.UserRequestMapper;
import com.example.Todo.DTO.DTOmapper.response.UserResponseMapper;
import com.example.Todo.DTO.requestDto.UserRequestDTO;
import com.example.Todo.DTO.responseDto.UserResponseDTO;
import com.example.Todo.Exceptions.UserException;
import com.example.Todo.Repositories.read.UserReadRepository;
import com.example.Todo.Repositories.write.UserWriteRepository;
import com.example.Todo.Model.User;
import com.example.Todo.Validations.UserValidations;
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
            UserValidations.validateUser(userDTO);
            User user = UserRequestMapper.dTOToUser(userDTO);
            try {

                user =userWriteRepo.save(user);
                if(user==null){
                    throw new UserException("Could not  be able to create user");

                }

                return UserResponseMapper.userToDTO(user);
            }
            catch (Exception e){
                throw new UserException("Could not be  able to create user",e);
            }


    }


   public UserResponseDTO updateUserByID(Long id,UserRequestDTO newUser) {
       UserValidations.validateUser(newUser);
        Optional<User> oldUserOpt= userReadRepo.findById(id);

        if(oldUserOpt.isPresent()){
            User oldUser=oldUserOpt.get();
            oldUser.setUserName(newUser.getUserName());
            try {
                userWriteRepo.save(oldUser);
            }
            catch (Exception e){
                throw new UserException("Could not be  able to update user details",e);
            }
            return UserResponseMapper.userToDTO(oldUser);
        }

            throw new UserException("User could not be found");




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
        throw new UserException("User could not be found");

    }

    public  UserResponseDTO deleteUser(Long id){
        Optional<User> optUserObject=userReadRepo.findById(id);

        if(optUserObject.isPresent()) {
            User user = optUserObject.get();
            try {
                userWriteRepo.deleteById(id);
            }
            catch(Exception e){
                throw new UserException("Could not delete user",e);
            }
            return UserResponseMapper.userToDTO(user);
        }
        throw new UserException("User could not be found");
    }

}
