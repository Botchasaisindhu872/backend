package com.example.Todo.DTOmapper.response;

import com.example.Todo.DTO.responseDto.TaskResponseDTO;
import com.example.Todo.Model.Task;

public class TaskResponseMapper {
    public static TaskResponseDTO taskToDTO(Task task){
        TaskResponseDTO taskResponseDTO =new TaskResponseDTO();
        taskResponseDTO.setTaskTitle(task.getTaskTitle());
        taskResponseDTO.setTaskDescription(task.getTaskDescription());
        taskResponseDTO.setDeadline(task.getDeadline());
        return taskResponseDTO;

    }
}
