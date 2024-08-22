package com.example.Todo.service;

import com.example.Todo.dto.requestDto.CategoryRequestDTO;
import com.example.Todo.dto.responseDto.CategoryResponseDTO;

import java.util.List;

public interface CategoryServiceInterface {
    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryDTO) ;
    public CategoryResponseDTO updateCategoryById(Long id, CategoryRequestDTO newCategory);
    public List<CategoryResponseDTO> getAllCategories();
    public CategoryResponseDTO getCategoryByID(Long c_id);
    public CategoryResponseDTO deleteCategoryById(Long c_id);
}
