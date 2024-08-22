package com.example.Todo.repositories.write;

import com.example.Todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWriteRepository extends JpaRepository<User,Long> {
}
