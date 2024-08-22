package com.example.Todo.dto.DTOmapper.response;

import com.example.Todo.dto.responseDto.CategoryResponseDTO;
import com.example.Todo.model.Category;


public class CategoryResponseMapper {

    public static CategoryResponseDTO categoryToDTO( Category category){
        CategoryResponseDTO categoryResponseDTO =new CategoryResponseDTO();
        categoryResponseDTO.setCategoryName(category.getCategoryName());

        return categoryResponseDTO;

    }
}
