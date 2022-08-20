package com.revature.pokebowl.order.dto.requests;

import com.revature.pokebowl.util.web.dto.EditResourceRequests;

import java.sql.Date;

public class EditOrderRequest extends EditResourceRequests {

    private int amount;
    private Date orderDate;
    private String orderAddress;
    private String orderZip;

    public EditOrderRequest() {
        super();
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
        return "EditOrderRequest{" +
                "amount=" + amount +
                ", orderDate=" + orderDate +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderZip='" + orderZip + '\'' +
                '}';
    }
}
