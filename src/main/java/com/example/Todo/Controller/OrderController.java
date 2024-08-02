package com.example.Todo.Controller;

import com.example.Todo.DTO.UserOrderDto;
import com.example.Todo.Repositories.OrdersRepository;

import com.example.Todo.model.Order;

import com.example.Todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private OrdersRepository orderRepo;
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){

        List<Order> orderList=new ArrayList<>();
        orderRepo.findAll().forEach(orderList::add);
        // userList.addAll(userRepo.findAll());

        return new ResponseEntity<>(orderList, HttpStatus.OK);


    }

    @GetMapping("orders/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable Long id){
        Optional<Order> orderData = orderRepo.findById(id);
        if (orderData.isPresent()){
            return new ResponseEntity<>(orderData.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order orderObj=orderRepo.save(order);

        return new ResponseEntity<>(orderObj,HttpStatus.OK);


    }
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateByID(@PathVariable Long id,@RequestBody Order newOrderData){
        Optional<Order> oldOrderData = orderRepo.findById(id);
        if (oldOrderData.isPresent()){
            Order updatedOrder=oldOrderData.get();
            updatedOrder.setOrderName(newOrderData.getOrderName());
            Order orderObj= orderRepo.save(updatedOrder);

            //return new ResponseEntity<>(userObj,HttpStatus.OK);
            return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Object> deleteOrderByID(@PathVariable Long id){

        orderRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
