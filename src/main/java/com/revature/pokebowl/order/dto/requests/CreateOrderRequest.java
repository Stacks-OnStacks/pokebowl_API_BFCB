package com.revature.pokebowl.order.dto.requests;

import java.sql.Date;

public class CreateOrderRequest {

    private int amount;
    private Date orderDate;
    private String orderAddress;
    private String orderZip;

    private String memberId;
    private String paymentId;

    public CreateOrderRequest() {
        super();
    }

    public CreateOrderRequest(int amount, Date orderDate, String orderAddress, String orderZip, String memberId, String paymentId) {
        this.amount = amount;
        this.orderDate = orderDate;
        this.orderAddress = orderAddress;
        this.orderZip = orderZip;
        this.memberId = memberId;
        this.paymentId = paymentId;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "amount=" + amount +
                ", orderDate=" + orderDate +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderZip='" + orderZip + '\'' +
                ", memberId='" + memberId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}