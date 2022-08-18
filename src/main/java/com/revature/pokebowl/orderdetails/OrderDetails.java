package com.revature.pokebowl.orderdetails;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.order.Order;

import javax.persistence.*;

@Entity
@Table(name="order_details")
public class OrderDetails {

    @Id
    @Column(name="order_details_id")
    private String orderDetailsId;

    @Column(name="quantity",nullable=false)
    private int quantity;

    @Column(name="comments",nullable=false)
    private String comments;

    @ManyToOne
    @JoinColumn(name="dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public OrderDetails(String orderDetailsId, int quantity, String comments) {
        this.orderDetailsId = orderDetailsId;
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsId='" + orderDetailsId + '\'' +
                ", quantity=" + quantity +
                ", comments='" + comments + '\'' +
                '}';
    }
}
