package com.example.Todo.Repositories.write;

import com.example.Todo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWriteRepository extends JpaRepository<User,Long> {
}
