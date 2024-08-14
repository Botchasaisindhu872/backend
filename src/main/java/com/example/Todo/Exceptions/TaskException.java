package com.example.Todo.Exceptions;

public class TaskException extends RuntimeException{
    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
