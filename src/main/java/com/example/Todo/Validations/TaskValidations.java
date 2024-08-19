package com.example.Todo.Validations;


import com.example.Todo.DTO.requestDto.TaskRequestDTO;
import com.example.Todo.Exceptions.TaskException;
import com.example.Todo.Exceptions.UserException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskValidations {
    public static void validateTask(TaskRequestDTO taskReqDTO){

        String title = taskReqDTO.getTaskTitle();
        String taskTitlePattern = "^[a-zA-Z]+.*";

        Pattern pattern = Pattern.compile(taskTitlePattern);
        Matcher matcher = pattern.matcher(title);

        if(title.length()>255 || title.length()<2){
            throw new TaskException("Task title Length should be between 2 and 255");
        }

        if (!matcher.matches()) {
            throw new TaskException("Enter a valid title which should start with a alphabet ");
        }
        String titleDesc = taskReqDTO.getTaskDescription();


        if(titleDesc.length()>500 || titleDesc.length()<2){
            throw new TaskException("Task description length should be between 2 and 255");
        }
        String deadlineString;
        try {
            System.out.println("HIII");
            LocalDate deadline = taskReqDTO.getDeadline();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            deadlineString = deadline.format(formatter);
            //System.out.println(deadlineString);
            //System.out.print(deadlineString);
        }
        catch(Exception e){
            throw new TaskException("Enter a valid deadline date.");

        }
        //Validate Date
        String deadlinePattern = "(([0-2][0-9])|(3[0-1]))-((0[1-9])|(1[0-2]))-([1-2][0-9][0-9][0-9])";

        pattern = Pattern.compile(deadlinePattern);
        matcher = pattern.matcher(deadlineString);
        if (!matcher.matches()) {
            throw new TaskException("Enter a valid deadline date.");
        }
        String day=deadlineString.substring(0,2);
        String month=deadlineString.substring(3,5);
        String year=deadlineString.substring(6,10);
        int deadlineDay;
        int deadlineMonth;
        int deadlineYear;
        try {
             deadlineDay = Integer.parseInt(day);
             deadlineMonth = Integer.parseInt(month);
             deadlineYear = Integer.parseInt(year);
        }
        catch(Exception e){
            throw new TaskException("Enter a valid deadline date.",e);
        }
        LocalDate currentDate = LocalDate.now();

        // Extract the day, month, and year
        int todayDay = currentDate.getDayOfMonth();
        int todayMonth = currentDate.getMonthValue();
        int todayYear = currentDate.getYear();
        System.out.println(deadlineString);
        System.out.println(deadlineMonth);
        System.out.println(deadlineYear);
        if(todayYear==deadlineYear && todayMonth == deadlineMonth && todayDay>deadlineDay || todayYear==deadlineYear && todayMonth > deadlineMonth||todayYear>deadlineYear  ){
            throw new TaskException("You are entering a expired deadline");

        }










    }
}
