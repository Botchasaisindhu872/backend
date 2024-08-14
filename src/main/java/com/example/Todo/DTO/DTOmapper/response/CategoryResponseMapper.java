package com.example.Todo.DTO.DTOmapper.response;

import com.example.Todo.DTO.responseDto.CategoryResponseDTO;
import com.example.Todo.Model.Category;


public class CategoryResponseMapper {

    public static CategoryResponseDTO categoryToDTO( Category category){
        CategoryResponseDTO categoryResponseDTO =new CategoryResponseDTO();
        categoryResponseDTO.setCategoryName(category.getCategoryName());

        return categoryResponseDTO;

    }
}
