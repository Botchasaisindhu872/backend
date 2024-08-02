package com.example.Todo.DTO;

public class UserOrderDto {
    private Long userID;
    private String userName;
    private String OrderName;

    public UserOrderDto() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public UserOrderDto(Long userID, String userName, String orderName) {
        this.userID = userID;
        this.userName = userName;
        OrderName = orderName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }
}
