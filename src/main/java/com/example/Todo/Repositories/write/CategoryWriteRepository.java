package com.example.Todo.Repositories.write;

import com.example.Todo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryWriteRepository extends JpaRepository<Category,Long> {
}
