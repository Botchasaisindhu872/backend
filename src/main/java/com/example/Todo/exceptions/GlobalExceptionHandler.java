package com.example.Todo.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
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
    @ExceptionHandler({InvalidFormatException.class, DateTimeParseException.class})
    public  ResponseEntity<Map<String, String>>  handleInvalidDateFormat(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error","enter a valid date" );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<Map<String, String>> handleCategoryException(CategoryException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Map<String, String>> handleNumberFormatException(NumberFormatException ex) {
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
