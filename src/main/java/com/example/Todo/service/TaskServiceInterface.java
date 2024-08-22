package com.example.Todo.service;

import com.example.Todo.dto.requestDto.TaskRequestDTO;
import com.example.Todo.dto.responseDto.TaskResponseDTO;

import java.util.List;

public interface TaskServiceInterface {
    public TaskResponseDTO addParentTask(Long u_id, Long c_id, TaskRequestDTO taskDTO);
    public TaskResponseDTO addSubTask(Long u_id, Long c_id, Long p_id, TaskRequestDTO taskDTO);
    public TaskResponseDTO updateTaskById(Long u_id, Long c_id, Long id, TaskRequestDTO newTask);
    public List<TaskResponseDTO> getParentTasks(Long u_id, Long c_id) ;
    public List<TaskResponseDTO> getAllSubTasks(Long parent_id) ;
    public TaskResponseDTO getTaskById(Long t_id);
    public TaskResponseDTO deleteTaskById(Long t_id);

}
