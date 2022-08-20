package com.revature.pokebowl.orderdetails.dto.requests;

public class CreateOrderDetailsRequest {

    private int quantity;
    private String comments;

    private String dishId;
    private String orderId;

    public CreateOrderDetailsRequest() {
        super();
    }

    public CreateOrderDetailsRequest(int quantity, String comments, String dishId, String orderId) {
        this.quantity = quantity;
        this.comments = comments;
        this.dishId = dishId;
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

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
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
                ", dishId='" + dishId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
