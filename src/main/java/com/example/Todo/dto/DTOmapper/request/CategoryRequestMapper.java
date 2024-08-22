package com.example.Todo.dto.DTOmapper.request;

import com.example.Todo.dto.requestDto.CategoryRequestDTO;
import com.example.Todo.model.Category;

public class  CategoryRequestMapper {
    public static CategoryRequestDTO categoryToDTO(Category category){
        CategoryRequestDTO categoryRequestDTO =new CategoryRequestDTO();
        categoryRequestDTO.setCategoryName(category.getCategoryName());

        return categoryRequestDTO;

    }
    public  static Category dTOToCategory(CategoryRequestDTO categoryRequestDTO){
        Category category =new Category();
        category.setCategoryName(categoryRequestDTO.getCategoryName());

        return category;

    }
}
