package com.example.Todo.validations;

import com.example.Todo.dto.requestDto.CategoryRequestDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryValidations {

    private static final Logger logger = LogManager.getLogger(CategoryValidations.class);

    public static void validateCategory(CategoryRequestDTO categoryReqDTO) {
        String name = categoryReqDTO.getCategoryName();
        String categoryNamePattern = "^[a-zA-Z]+[a-zA-Z0-9]*";

        Pattern pattern = Pattern.compile(categoryNamePattern);
        Matcher matcher = pattern.matcher(name);

        // Validate category name length
        if (name.length() > 255 || name.length() < 2) {
            String errorMessage = "Category name length should be between 2 and 255 characters.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        // Validate if category name is alphanumeric and starts with a letter
        if (!matcher.matches()) {
            String errorMessage = "Invalid category name. It should be alphanumeric and should not start with a number.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        logger.info("Category validation passed for name: {}", name);
    }
}
