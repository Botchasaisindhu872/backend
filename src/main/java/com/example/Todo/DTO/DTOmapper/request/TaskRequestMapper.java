package com.example.Todo.DTO.DTOmapper.request;

import com.example.Todo.DTO.requestDto.TaskRequestDTO;
import com.example.Todo.Model.Task;

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
