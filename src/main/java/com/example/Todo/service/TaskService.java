package com.example.Todo.service;

import com.example.Todo.dto.requestDto.TaskRequestDTO;
import com.example.Todo.dto.responseDto.TaskResponseDTO;
import com.example.Todo.dto.DTOmapper.request.TaskRequestMapper;
import com.example.Todo.dto.DTOmapper.response.TaskResponseMapper;
import com.example.Todo.repositories.RepositoryFactory;
import com.example.Todo.repositories.read.TaskReadRepository;
import com.example.Todo.repositories.write.TaskWriteRepository;
import com.example.Todo.model.Task;
import com.example.Todo.validations.TaskValidations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public  class TaskService implements TaskServiceInterface {
    private static final Logger logger = LogManager.getLogger(TaskService.class);


    private final TaskReadRepository taskReadRepo;
    private final TaskWriteRepository taskWriteRepo;


    @Autowired
    public TaskService(RepositoryFactory repositoryFactory) {
        this.taskReadRepo = repositoryFactory.getTaskReadRepo();
        this.taskWriteRepo = repositoryFactory.getTaskWriteRepo();
    }

    @Override
    public TaskResponseDTO addParentTask(Long u_id, Long c_id, TaskRequestDTO taskDTO) {
        logger.info("Adding parent task with userId: {} and categoryId: {}", u_id, c_id);


        TaskValidations.validateTask(taskDTO);
        Task task = TaskRequestMapper.dTOToTask(taskDTO);
        task.setUserId(u_id);
        task.setParentTaskId(task.getTaskId());
        task.setCategoryId(c_id);

        try {
            taskWriteRepo.save(task);
            logger.info("Parent task successfully added with ID: {}", task.getTaskId());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid task data. Could not create parent task.", e);
            throw new IllegalArgumentException("Invalid task data. Could not create parent task.", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while saving the task.", e);
            throw new RuntimeException("Unexpected error occurred while saving the task.", e);
        }

        return TaskResponseMapper.taskToDTO(task);
    }







@Override
    public TaskResponseDTO addSubTask(Long u_id, Long c_id, Long p_id, TaskRequestDTO taskDTO) {
        logger.info("Adding sub-task with userId: {}, categoryId: {}, parentId: {}", u_id, c_id, p_id);

        TaskValidations.validateTask(taskDTO);
        Task task = TaskRequestMapper.dTOToTask(taskDTO);
        task.setUserId(u_id);
        task.setParentTaskId(p_id);
        task.setCategoryId(c_id);

        try {
            taskWriteRepo.save(task);
            logger.info("Sub-task successfully added with ID: {}", task.getTaskId());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid task data. Could not create sub task.", e);
            throw new IllegalArgumentException("Invalid task data. Could not create sub task.", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while saving the sub-task.", e);
            throw new RuntimeException("Unexpected error occurred while saving the sub-task.", e);
        }

        return TaskResponseMapper.taskToDTO(task);
    }
    @Override
    public TaskResponseDTO updateTaskById(Long u_id, Long c_id, Long id, TaskRequestDTO newTask) {
        logger.info("Updating task with ID: {}", id);

        TaskValidations.validateTask(newTask);
        Optional<Task> oldTaskOpt = taskReadRepo.findById(id);

        if (oldTaskOpt.isEmpty()) {
            logger.error("Task with ID {} not found.", id);
            throw new NoSuchElementException("Task with ID " + id + " not found.");
        }

        Task oldTask = oldTaskOpt.get();
        oldTask.setTaskTitle(newTask.getTaskTitle());
        oldTask.setTaskDescription(newTask.getTaskDescription());
        oldTask.setDeadline(newTask.getDeadline());

        try {
            taskWriteRepo.save(oldTask);
            logger.info("Task successfully updated with ID: {}", id);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid task data. Could not update task.", e);
            throw new IllegalArgumentException("Invalid task data. Could not update task.", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating the task.", e);
            throw new RuntimeException("Unexpected error occurred while updating the task.", e);
        }

        return TaskResponseMapper.taskToDTO(oldTask);
    }

    @Override
    public List<TaskResponseDTO> getParentTasks(Long u_id, Long c_id) {
        logger.info("Fetching parent tasks with userId: {} and categoryId: {}", u_id, c_id);

        List<Task> taskList = taskReadRepo.getAllParentTasks(u_id, c_id);
        logger.info("Fetched parent tasks with userId: {} and categoryId: {} successfully", u_id, c_id);

        List<TaskResponseDTO> taskDTOList = new ArrayList<>();

        for (Task task : taskList) {
            taskDTOList.add(TaskResponseMapper.taskToDTO(task));
        }


        return taskDTOList;
    }

    @Override
    public List<TaskResponseDTO> getAllSubTasks(Long parent_id) {
        logger.info("Fetching sub-tasks for parent task ID: {}", parent_id);

        getTaskById(parent_id); // Ensure the parent task exists

        List<Task> subTaskList = taskReadRepo.getAllSubTasks(parent_id);
        logger.info("Fetched sub-tasks for parent task ID: {} successfully", parent_id);

        List<TaskResponseDTO> subTaskDTOList = new ArrayList<>();

        for (Task subtask : subTaskList) {
            subTaskDTOList.add(TaskResponseMapper.taskToDTO(subtask));
        }

        return subTaskDTOList;
    }
    @Override
    public TaskResponseDTO getTaskById(Long t_id) {
        logger.info("Fetching task with ID: {}", t_id);

        Optional<Task> optTaskObject = taskReadRepo.findById(t_id);

        if (optTaskObject.isEmpty()) {
            logger.error("Task with ID {} not found.", t_id);
            throw new NoSuchElementException("Task with ID " + t_id + " not found.");
        }

        Task task = optTaskObject.get();
        logger.info("Fetched task successfully with ID: {}", t_id);
        return TaskResponseMapper.taskToDTO(task);
    }
    @Override
    public TaskResponseDTO deleteTaskById(Long t_id) {
        logger.info("Deleting task with ID: {}", t_id);
        logger.debug("Hii debug");

        Optional<Task> optTaskObject = taskReadRepo.findById(t_id);

        if (optTaskObject.isEmpty()) {
            logger.error("Task with ID {} not found .", t_id);
            throw new NoSuchElementException("Task with ID " + t_id + " not found.");
        }

        Task task = optTaskObject.get();
        try {
            taskWriteRepo.deleteById(t_id);
            logger.info("Task successfully deleted with ID: {}", t_id);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid task ID. Could not delete task.", e);
            throw new IllegalArgumentException("Invalid task ID. Could not delete task.", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting the task.", e);
            throw new RuntimeException("Unexpected error occurred while deleting the task.", e);
        }

        return TaskResponseMapper.taskToDTO(task);
    }
}
