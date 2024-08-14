package com.example.Todo.Validations;
import java.util.regex.*;

import com.example.Todo.DTO.requestDto.UserRequestDTO;
import com.example.Todo.DTO.responseDto.UserResponseDTO;
import com.example.Todo.Exceptions.UserException;

public class UserValidations {
    public static void validateUser(UserRequestDTO userReqDTO){
        String name = userReqDTO.getUserName();
        String userNamePattern = "^[a-zA-Z]+[a-zA-Z0-9]*";

        Pattern pattern = Pattern.compile(userNamePattern);
        Matcher matcher = pattern.matcher(name);

        if(name.length()>255 || name.length()<2){
            throw new UserException("Name Length should be between 2 and 255");
        }

        if (!matcher.matches()) {
                throw new UserException("Enter a valid name . Name  shouldd be alphanumeric and should not start with a number ");
        }
    }

}
