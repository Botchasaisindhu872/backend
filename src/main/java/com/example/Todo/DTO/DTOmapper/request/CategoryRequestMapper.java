package com.example.Todo.DTO.DTOmapper.request;

import com.example.Todo.DTO.requestDto.CategoryRequestDTO;
import com.example.Todo.Model.Category;

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
