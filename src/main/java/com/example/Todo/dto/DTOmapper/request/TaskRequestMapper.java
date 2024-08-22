package com.example.Todo.dto.DTOmapper.request;

import com.example.Todo.dto.requestDto.TaskRequestDTO;
import com.example.Todo.model.Task;

public class TaskRequestMapper {
    public static TaskRequestDTO taskToDTO(Task task){
        TaskRequestDTO taskRequestDTO =new TaskRequestDTO();
        taskRequestDTO.setTaskTitle(task.getTaskTitle());
        taskRequestDTO.setTaskDescription(task.getTaskDescription());
        taskRequestDTO.setDeadline(task.getDeadline());
        return taskRequestDTO;

    }
    public static Task dTOToTask(TaskRequestDTO taskRequestDTO){
        Task task =new Task();
        task.setTaskTitle(taskRequestDTO.getTaskTitle());
        task.setTaskDescription(taskRequestDTO.getTaskDescription());
        task.setDeadline(taskRequestDTO.getDeadline());
        return task;

    }

}
