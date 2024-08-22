package com.example.Todo.dto.DTOmapper.response;

import com.example.Todo.dto.responseDto.TaskResponseDTO;
import com.example.Todo.model.Task;

public class TaskResponseMapper {
    public static TaskResponseDTO taskToDTO(Task task){
        TaskResponseDTO taskResponseDTO =new TaskResponseDTO();
        taskResponseDTO.setTaskId(task.getTaskId());
        taskResponseDTO.setTaskTitle(task.getTaskTitle());
        taskResponseDTO.setTaskDescription(task.getTaskDescription());
        taskResponseDTO.setDeadline(task.getDeadline());
        return taskResponseDTO;

    }
}
