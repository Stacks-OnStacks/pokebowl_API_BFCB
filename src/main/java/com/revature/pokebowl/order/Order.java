package com.revature.pokebowl.order;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @Column(name="order_id")
    private String orderId;

    @Column(name="amount",nullable=false)
    private int amount;

    @Column(name="order_date",nullable=false)
    private Date orderDate;

    @Column(name="order_address",nullable=false)
    private String orderAddress;

    @Column(name="order_zip",nullable=false)
    private String orderZip;

    @Column(name="customer_username",nullable=false)
    private String customerUsername;

    @Column(name="payment_id",nullable=false)
    private String paymentId;

    public Order(String orderId, int amount, Date orderDate, String orderAddress, String orderZip, String customerUsername, String paymentId) {
        this.orderId = orderId;
        this.amount = amount;
        this.orderDate = orderDate;
        this.orderAddress = orderAddress;
        this.orderZip = orderZip;
        this.customerUsername = customerUsername;
        this.paymentId = paymentId;
    }

    public Order() {
        super();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderZip() {
        return orderZip;
    }

    public void setOrderZip(String orderZip) {
        this.orderZip = orderZip;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", orderDate=" + orderDate +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderZip='" + orderZip + '\'' +
                ", customerUsername='" + customerUsername + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
