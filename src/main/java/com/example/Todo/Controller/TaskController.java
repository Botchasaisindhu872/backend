package com.example.Todo.Controller;

import com.example.Todo.DTO.requestDto.TaskRequestDTO;
import com.example.Todo.DTO.responseDto.TaskResponseDTO;
import com.example.Todo.Model.Task;
import com.example.Todo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    //GET MAPPINGS

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getParentTasks(@RequestParam Long u_id, @RequestParam Long c_id){

        List<TaskResponseDTO> taskDTOList = taskService.getParentTasks(u_id,c_id);
        return  new ResponseEntity<>(taskDTOList, HttpStatus.OK);


    }
    @GetMapping("/{t_id}")
    public ResponseEntity<TaskResponseDTO> getParentTaskById(@RequestParam Long t_id){


        TaskResponseDTO taskDTO= taskService.getTaskById(t_id);
        return  new ResponseEntity<>(taskDTO, HttpStatus.OK);

    }

    @GetMapping("/{t_id}/subtasks")
    public ResponseEntity<List<TaskResponseDTO>> getSubTasks(@PathVariable Long t_id){

        List<TaskResponseDTO> subTaskListDTO= taskService.getAllSubTasks(t_id);
        return  new ResponseEntity<>(subTaskListDTO, HttpStatus.OK);


    }

    @GetMapping("/{t_id}/subtasks/{sub_task_id}")
    public ResponseEntity<TaskResponseDTO> getSubTaskById(@PathVariable Long t_id,@PathVariable Long sub_task_id){

        TaskResponseDTO taskDTO= taskService.getTaskById(sub_task_id);
        return  new ResponseEntity<>(taskDTO, HttpStatus.OK);



    }

    //POST MAPPINGS
    @PostMapping
    public ResponseEntity<TaskResponseDTO> addParentTask(@RequestParam Long u_id,@RequestParam Long c_id,@RequestBody TaskRequestDTO taskData){
        TaskResponseDTO taskDTO= taskService.addParentTask(u_id,c_id,taskData);
        return  new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @PostMapping("/{t_id}/subtasks")
    public ResponseEntity<TaskResponseDTO> addSubTask(@RequestParam Long u_id,@RequestParam Long c_id,@PathVariable Long t_id,@RequestBody TaskRequestDTO taskData){

        TaskResponseDTO taskDTO= taskService.addSubTask(u_id,c_id,t_id,taskData);
        return  new ResponseEntity<>(taskDTO, HttpStatus.OK);


    }

    //PUT MAPPINGS
    @PutMapping("/{t_id}")
    public ResponseEntity<TaskResponseDTO> updateTaskById(@RequestParam Long u_id,@RequestParam Long c_id,@PathVariable Long t_id,@RequestBody TaskRequestDTO newTaskData){

        TaskResponseDTO task= taskService.updateTaskById(u_id,c_id,t_id,newTaskData);
        return new ResponseEntity<>(task, HttpStatus.OK);


    }

    //Delete Mappings
    @DeleteMapping("/{t_id}")
    public ResponseEntity<TaskResponseDTO> deleteTaskById(@PathVariable Long t_id){
        TaskResponseDTO taskDTO= taskService.deleteTaskById(t_id);
        return  new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }


}
