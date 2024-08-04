package com.example.Todo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category  {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> subTasks;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // Constructors, getters, and setters
    public Category() {}

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
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

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
