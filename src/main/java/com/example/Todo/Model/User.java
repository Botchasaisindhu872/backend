package com.example.Todo.Model;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="user_id")
    private Long userId;
   // @Size(min = 2, max = 255, message = "Name must be between 2 and 50 characters")
    @Column(name="user_name")
    private String userName;


    // Constructors, getters, and setters
    public User() {}

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
