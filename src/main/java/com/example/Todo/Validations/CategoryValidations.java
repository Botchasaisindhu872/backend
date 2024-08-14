package com.example.Todo.Validations;

import com.example.Todo.DTO.requestDto.CategoryRequestDTO;
import com.example.Todo.Exceptions.CategoryException;
import com.example.Todo.Exceptions.UserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryValidations {
    public  static void validateCategory(CategoryRequestDTO categoryReqDTO){
        String name = categoryReqDTO.getCategoryName();
        String categoryNamePattern = "^[a-zA-Z]+[a-zA-Z0-9]*";

        Pattern pattern = Pattern.compile(categoryNamePattern);
        Matcher matcher = pattern.matcher(name);

        if(name.length()>255 || name.length()<2){
            throw new CategoryException("Category Name Length should be between 2 and 255");
        }

        if (!matcher.matches()) {
            throw new CategoryException("Enter a valid category name . Category name  shouldd be alphanumeric and should not start with a number ");
        }

    }
}
