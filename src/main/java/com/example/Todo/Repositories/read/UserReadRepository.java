package com.example.Todo.Repositories.read;

import com.example.Todo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReadRepository extends JpaRepository<User,Long> {
}
