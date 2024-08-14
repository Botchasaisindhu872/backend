package com.example.Todo.Service;

import com.example.Todo.DTO.requestDto.TaskRequestDTO;
import com.example.Todo.DTO.responseDto.TaskResponseDTO;
import com.example.Todo.DTO.DTOmapper.request.TaskRequestMapper;
import com.example.Todo.DTO.DTOmapper.response.TaskResponseMapper;
import com.example.Todo.Exceptions.TaskException;
import com.example.Todo.Repositories.read.TaskReadRepository;
import com.example.Todo.Repositories.write.TaskWriteRepository;
import com.example.Todo.Model.Task;
import com.example.Todo.Validations.TaskValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
     @Autowired
    private  TaskReadRepository taskReadRepo;
    @Autowired
    private  TaskWriteRepository taskWriteRepo;
    public  TaskResponseDTO addParentTask(Long u_id, Long c_id, TaskRequestDTO taskDTO){

        TaskValidations.validateTask(taskDTO);
        Task task= TaskRequestMapper.dTOToTask(taskDTO);
        task.setUserId(u_id);
        task.setParentTaskId(task.getTaskId());
        task.setCategoryId(c_id);
        try {
            taskWriteRepo.save(task);
        }

        catch(Exception e){
            throw new TaskException("Could not create parent task",e);
        }
       return TaskResponseMapper.taskToDTO(task);

    }
    public  TaskResponseDTO addSubTask(Long u_id, Long c_id,  Long p_id,  TaskRequestDTO taskDTO){
        TaskValidations.validateTask(taskDTO);
        Task task= TaskRequestMapper.dTOToTask(taskDTO);
        task.setUserId(u_id);
        task.setParentTaskId(p_id);
        task.setCategoryId(c_id);
        try {
            taskWriteRepo.save(task);
        }
        catch(Exception e){
            throw new TaskException("Could not create sub task",e);
        }

        return TaskResponseMapper.taskToDTO(task);
    }
    public  TaskResponseDTO updateTaskById(Long u_id, Long c_id,  Long id,  TaskRequestDTO newTask){
        TaskValidations.validateTask(newTask);
        Optional<Task> oldTaskOpt= taskReadRepo.findById(id);
        Task oldTask;
        if(oldTaskOpt.isPresent()){
            oldTask=oldTaskOpt.get();
            oldTask.setTaskTitle(newTask.getTaskTitle());
            oldTask.setTaskDescription(newTask.getTaskDescription());
            oldTask.setDeadline(newTask.getDeadline());
            try{
                taskWriteRepo.save(oldTask);
            }
            catch(Exception e ){
                throw new TaskException("Could not update task",e);
            }
            return  TaskResponseMapper.taskToDTO(oldTask);

        }



        throw new TaskException("Could not find task");


    }


    public  List<TaskResponseDTO>  getParentTasks (Long u_id, Long c_id){
        List<Task> taskList=taskReadRepo.getAllParentTasks(u_id,c_id);
        List<TaskResponseDTO> taskDTOList=new ArrayList<>();
        for (Task task : taskList)
        {
            taskDTOList.add(TaskResponseMapper.taskToDTO(task));

        }

        return taskDTOList;


    }
    public  List<TaskResponseDTO>  getAllSubTasks (Long parent_id){
        TaskResponseDTO taskDTO=getTaskById(parent_id);

        List<Task> subTaskList=taskReadRepo.getAllSubTasks(parent_id);
        List<TaskResponseDTO> subTaskDTOList=new ArrayList<>();
        for (Task subtask : subTaskList)
        {
            subTaskDTOList.add(TaskResponseMapper.taskToDTO(subtask));

        }


        return subTaskDTOList;

    }



    public  TaskResponseDTO getTaskById(Long t_id){
        Optional<Task> optTaskObject=taskReadRepo.findById(t_id);

        if(optTaskObject.isPresent()) {
            Task task = optTaskObject.get();

            return TaskResponseMapper.taskToDTO(task);
        }
        throw new TaskException("Could not find task");

    }





    public  TaskResponseDTO deleteTaskById(Long t_id){
        Optional<Task> optTaskObject=taskReadRepo.findById(t_id);

        if(optTaskObject.isPresent()) {
            Task task = optTaskObject.get();
            try {
                taskWriteRepo.deleteById(t_id);
            }
            catch (Exception e){
                throw new TaskException("Could not delete task",e);
            }
            return TaskResponseMapper.taskToDTO(task);
        }
        throw new TaskException("Could not find task");
    }



}

