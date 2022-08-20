package com.revature.pokebowl.orderdetails.dto.requests;

import com.revature.pokebowl.util.web.dto.EditResourceRequests;

public class EditOrderDetailsRequest extends EditResourceRequests {

    private int quantity;
    private String comments;

    private String dishId;
    private String orderId;

    public EditOrderDetailsRequest() {
        super();
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
        return "EditOrderDetailsRequest{" +
                "quantity=" + quantity +
                ", comments='" + comments + '\'' +
                ", dishId='" + dishId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
