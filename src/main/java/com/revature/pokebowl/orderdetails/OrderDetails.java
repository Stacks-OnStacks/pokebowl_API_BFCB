package com.revature.pokebowl.orderdetails;

import javax.persistence.*;

@Entity
@Table(name="order_details")
public class OrderDetails {

    @Id
    @Column(name="order_details_id")
    private String orderDetailsId;

    @Column(name="dish_id",nullable=false)
    private String dishId;

    @Column(name="order_id",nullable=false)
    private String orderId;

    @Column(name="quantity",nullable=false)
    private int quantity;

    @Column(name="comments",nullable=false)
    private String comments;

    public OrderDetails(String orderDetailsId, String dishId, String orderId, int quantity, String comments) {
        this.orderDetailsId = orderDetailsId;
        this.dishId = dishId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.comments = comments;
    }

    public OrderDetails() {
        super();
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
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

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsId='" + orderDetailsId + '\'' +
                ", dishId='" + dishId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", quantity=" + quantity +
                ", comments='" + comments + '\'' +
                '}';
    }
}
