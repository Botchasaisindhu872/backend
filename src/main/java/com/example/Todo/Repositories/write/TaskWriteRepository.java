package com.example.Todo.Repositories.write;

import com.example.Todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskWriteRepository extends JpaRepository<Task,Long> {
}
