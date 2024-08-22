package com.example.Todo.repositories.read;

import com.example.Todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReadRepository extends JpaRepository<User,Long> {
}
