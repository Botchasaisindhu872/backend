package com.example.Todo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="tasks")
public class Task  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="task_id")
    private Long taskId;

    @Column(name="task_title")
    private String taskTitle;
    @Column(name="task_desc")
    private String taskDescription;
    @Column(name="task_deadline")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date deadline;

    @Column(name="user_id")
    private Long userId;
    @Column(name="parent_task_id")
    private Long parentTaskId;
    @Column(name="category_id")
    private Long categoryId;
    //Constructors
    public Task() {
    }

    public Task(Long taskId, String taskTitle, String taskDescription, Date deadline, Long userId, Long parentTaskId, Long categoryId) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.userId = userId;
        this.parentTaskId = parentTaskId;
        this.categoryId = categoryId;
    }

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

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
