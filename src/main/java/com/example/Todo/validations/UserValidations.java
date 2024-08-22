package com.example.Todo.validations;

import com.example.Todo.dto.requestDto.UserRequestDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidations {

    private static final Logger logger = LogManager.getLogger(UserValidations.class);

    public static void validateUser(UserRequestDTO userReqDTO) {
        String name = userReqDTO.getUserName();
        String userNamePattern = "^[a-zA-Z]+[a-zA-Z0-9]*";

        Pattern pattern = Pattern.compile(userNamePattern);
        Matcher matcher = pattern.matcher(name);

        if (name.length() > 255 || name.length() < 2) {
            String errorMessage = "Name length should be between 2 and 255 characters.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (!matcher.matches()) {
            String errorMessage = "Invalid name. The name should be alphanumeric and should not start with a number.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        logger.info("User validation passed for name: {}", name);
    }
}
