package com.example.Todo.model;

import jakarta.persistence.*;


@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String orderName;
   // @ManyToOne
    //@JoinColumn(name = "fk_userId")
    //private User user;


    public Order() {
    }

    public Order(Long orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
