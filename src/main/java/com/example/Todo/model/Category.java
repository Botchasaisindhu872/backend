package com.example.Todo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category  {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="category_id")
    private Long categoryId;
    @Column(name = "category_name")
    private String categoryName;

    // Constructors, getters, and setters
    public Category() {}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
