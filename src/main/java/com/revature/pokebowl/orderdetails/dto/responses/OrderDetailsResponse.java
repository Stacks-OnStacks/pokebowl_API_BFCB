package com.revature.pokebowl.orderdetails.dto.responses;


public class OrderDetailsResponse {

    private String orderDetailsId;
    private int quantity;
    private String comments;

    private String dishId;
    private String orderId;

    public OrderDetailsResponse() {
        super();
    }

    public OrderDetailsResponse(String orderDetailsId, int quantity, String comments, String dishId, String orderId) {
        this.orderDetailsId = orderDetailsId;
        this.quantity = quantity;
        this.comments = comments;
        this.dishId = dishId;
        this.orderId = orderId;
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
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
        return "OrderDetailsResponse{" +
                "orderDetailsId='" + orderDetailsId + '\'' +
                ", quantity=" + quantity +
                ", comments='" + comments + '\'' +
                ", dishId='" + dishId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
