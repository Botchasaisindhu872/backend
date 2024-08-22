package com.example.Todo.validations;

import com.example.Todo.dto.requestDto.TaskRequestDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskValidations {

    private static final Logger logger = LogManager.getLogger(TaskValidations.class);

    public static void validateTask(TaskRequestDTO taskReqDTO) {
        String title = taskReqDTO.getTaskTitle();
        String taskTitlePattern = "^[a-zA-Z]+.*";

        Pattern pattern = Pattern.compile(taskTitlePattern);
        Matcher matcher = pattern.matcher(title);

        if (title.length() > 255 || title.length() < 2) {
            String errorMessage = "Task title length should be between 2 and 255 characters.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        if (!matcher.matches()) {
            String errorMessage = "Task title must start with an alphabet.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        String description = taskReqDTO.getTaskDescription();

        if (description.length() > 500 || description.length() < 2) {
            String errorMessage = "Task description length should be between 2 and 500 characters.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        String deadlineString;
        try {
            LocalDate deadline = taskReqDTO.getDeadline();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            deadlineString = deadline.format(formatter);
        } catch (Exception e) {
            String errorMessage = "Invalid deadline date format.";
            logger.error(errorMessage, e);
            throw new IllegalArgumentException(errorMessage, e);
        }

        String deadlinePattern = "(([0-2][0-9])|(3[0-1]))-((0[1-9])|(1[0-2]))-([1-2][0-9][0-9][0-9])";
        pattern = Pattern.compile(deadlinePattern);
        matcher = pattern.matcher(deadlineString);
        if (!matcher.matches()) {
            String errorMessage = "Invalid deadline date format. Please use dd-MM-yyyy.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        String day = deadlineString.substring(0, 2);
        String month = deadlineString.substring(3, 5);
        String year = deadlineString.substring(6, 10);
        int deadlineDay;
        int deadlineMonth;
        int deadlineYear;

        try {
            deadlineDay = Integer.parseInt(day);
            deadlineMonth = Integer.parseInt(month);
            deadlineYear = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            String errorMessage = "Invalid date components in deadline. Deadline must contain only numbers.";
            logger.error(errorMessage, e);
            throw new NumberFormatException(errorMessage);
        }

        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(LocalDate.of(deadlineYear, deadlineMonth, deadlineDay))) {
            String errorMessage = "Deadline cannot be in the past.";
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        logger.info("Task validation passed for title: {}", title);
    }
}
