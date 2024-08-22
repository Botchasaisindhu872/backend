package com.example.Todo.repositories.read;

import com.example.Todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskReadRepository extends JpaRepository<Task,Long> {
@Query(value = "SELECT t FROM Task t WHERE t.parentTaskId=:pt_id ")
List<Task> getAllSubTasks(@Param("pt_id") Long pt_id);


@Query("SELECT t FROM Task t WHERE t.userId=:uid AND t.categoryId=:cid and t.parentTaskId IS NULL")
List<Task> getAllParentTasks(@Param("uid") Long u_id,@Param("cid") Long c_id);

}
