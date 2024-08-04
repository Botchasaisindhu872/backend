package com.example.Todo.Repositories.read;

import com.example.Todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskReadRepository extends JpaRepository<Task,Long> {
}
