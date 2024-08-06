package com.example.Todo.Repositories.write;

import com.example.Todo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryWriteRepository extends JpaRepository<Category,Long> {
}
