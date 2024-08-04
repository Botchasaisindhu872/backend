package com.example.Todo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tasks")
public class Task  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long taskId;



    private String taskTitle;


    //one-to-many relations
    @OneToMany(mappedBy = "parentTask" ,cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Task> subTasks;

    //many-to-one relations

    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;
    @ManyToOne
    @JoinColumn(name="parentTaskId")
    private Task parentTask;



    //Constructors
    public Task() {
    }

    public Task(Long taskId, String taskTitle) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
    }


    //Getters and Setters

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }
}
