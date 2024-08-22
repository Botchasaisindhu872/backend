package com.example.Todo.service;

import com.example.Todo.dto.DTOmapper.request.UserRequestMapper;
import com.example.Todo.dto.DTOmapper.response.UserResponseMapper;
import com.example.Todo.dto.requestDto.UserRequestDTO;
import com.example.Todo.dto.responseDto.UserResponseDTO;
import com.example.Todo.repositories.RepositoryFactory;
import com.example.Todo.repositories.read.UserReadRepository;
import com.example.Todo.repositories.write.UserWriteRepository;
import com.example.Todo.model.User;
import com.example.Todo.validations.UserValidations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserReadRepository userReadRepo;
    private final UserWriteRepository userWriteRepo;


    @Autowired
    public UserService(RepositoryFactory repositoryFactory) {
        this.userReadRepo = repositoryFactory.getUserReadRepo();
        this.userWriteRepo = repositoryFactory.getUserWriteRepo();
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userDTO) {
        logger.info("Attempting to add user with data: {}", userDTO);

        UserValidations.validateUser(userDTO);
        User user = UserRequestMapper.dTOToUser(userDTO);

        try {
            user = userWriteRepo.save(user);
            if (user == null) {
                logger.error("Failed to create user. User object is null after save.");
                throw new RuntimeException("Could not be able to create user");
            }

            logger.info("User successfully created with ID: {}", user.getUserId());
            return UserResponseMapper.userToDTO(user);
        } catch (RuntimeException e) {
            logger.error("Could not be able to create user", e);
            throw new RuntimeException("Could not be able to create user", e);
        }
    }

    @Override
    public UserResponseDTO updateUserByID(Long id, UserRequestDTO newUser) {
        logger.info("Attempting to update user with ID: {} and new data: {}", id, newUser);

        UserValidations.validateUser(newUser);
        Optional<User> oldUserOpt = userReadRepo.findById(id);
        try {
            if (oldUserOpt.isPresent()) {
                User oldUser = oldUserOpt.get();
                oldUser.setUserName(newUser.getUserName());


                userWriteRepo.save(oldUser);
                logger.info("User successfully updated with ID: {}", id);
                return UserResponseMapper.userToDTO(oldUser);

            }

            logger.error("User with ID: {} not found", id);
            throw new NoSuchElementException("User could not be found");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User could not be found");

        }
        catch (RuntimeException e) {
            logger.error("Could not update user", e);
            throw new RuntimeException("Could not update user", e);
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        logger.info("Fetching all users");

        List<User> userList = userReadRepo.findAll();
        logger.info("Fetching all users is successful");
        List<UserResponseDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            userDTOList.add(UserResponseMapper.userToDTO(user));
        }

        return userDTOList;
    }

    @Override
    public UserResponseDTO getUserByID(Long id) {
        logger.info("Fetching user with ID: {}", id);

        Optional<User> optUserObject = userReadRepo.findById(id);
        try {
            if (optUserObject.isPresent()) {
                User user = optUserObject.get();
                logger.info("Fetching user with ID: {} is successful", id);
                return UserResponseMapper.userToDTO(user);
            }


            logger.error("User with ID: {} not found", id);
            throw new NoSuchElementException("User could not be found");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User could not be found");
        }
    }

    @Override
    public UserResponseDTO deleteUser(Long id) {
        logger.info("Attempting to delete user with ID: {}", id);

        Optional<User> optUserObject = userReadRepo.findById(id);
        try {
            if (optUserObject.isPresent()) {
                User user = optUserObject.get();
                userWriteRepo.deleteById(id);
                logger.info("User successfully deleted with ID: {}", id);
                return UserResponseMapper.userToDTO(user);


            }

            logger.error("User with ID: {} not found", id);
            throw new NoSuchElementException("User could not be found");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User could not be found");
        } catch (RuntimeException e) {
            logger.error("Could not delete user", e);
            throw new RuntimeException("Could not delete user", e);
        }
    }
}


