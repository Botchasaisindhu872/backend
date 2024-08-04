package com.example.Todo.Repositories.read;

import com.example.Todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReadRepository extends JpaRepository<User,Long> {
}
