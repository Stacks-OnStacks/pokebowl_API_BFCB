package com.revature.pokebowl.orderdetails.dto.requests;

public class CreateOrderDetailsRequest {

    private int quantity;
    private String comments;
    private String dishName;
    private String orderId;

    public CreateOrderDetailsRequest() {
        super();
    }

    public CreateOrderDetailsRequest(int quantity, String comments, String dishName, String orderId) {
        this.quantity = quantity;
        this.comments = comments;
        this.dishName = dishName;
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CreateOrderDetailsRequest{" +
                "quantity=" + quantity +
                ", comments='" + comments + '\'' +
                ", dishName='" + dishName + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
