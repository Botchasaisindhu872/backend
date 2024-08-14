package com.example.Todo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(UserException.class)
   public ResponseEntity< Map<String, String>> handleUserException(UserException ex) {
       Map<String, String> response = new HashMap<>();

       response.put("error", ex.getMessage());
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }
    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<Map<String, String>> handleCategoryException(CategoryException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TaskException.class)
    public ResponseEntity<Map<String, String>> handleTaskException(TaskException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
