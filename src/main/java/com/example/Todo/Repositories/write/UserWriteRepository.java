package com.example.Todo.Repositories.write;

import com.example.Todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWriteRepository extends JpaRepository<User,Long> {
}
