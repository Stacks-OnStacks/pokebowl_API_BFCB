package com.revature.pokebowl.order.dto.requests;

import java.sql.Date;

public class CreateOrderRequest {

    private int amount;
    private Date orderDate;
    private String orderAddress;
    private String orderZip;

    public CreateOrderRequest() {
        super();
    }

    public CreateOrderRequest(int amount, Date orderDate, String orderAddress, String orderZip) {
        this.amount = amount;
        this.orderDate = orderDate;
        this.orderAddress = orderAddress;
        this.orderZip = orderZip;
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

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "amount=" + amount +
                ", orderDate=" + orderDate +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderZip='" + orderZip + '\'' +
                '}';
    }
}
