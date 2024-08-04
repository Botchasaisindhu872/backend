package com.example.Todo.Repositories.read;

import com.example.Todo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReadRepository extends JpaRepository<Category,Long> {
}
