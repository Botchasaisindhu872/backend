package com.example.Todo.Repositories;

import com.example.Todo.DTO.UserOrderDto;
import com.example.Todo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Order,Long> {
    //@Query( "SELECT new UserOrderDTO(u.userName,o.orderName) FROM User u JOIN Order o")
    ///public List<UserOrderDto> findUserOrderDetails();



}
