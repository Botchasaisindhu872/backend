package com.example.Todo.dto.requestDto;

public class CategoryRequestDTO {
    public String categoryName;

    public CategoryRequestDTO() {
    }

    public CategoryRequestDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
