package com.revature.pokebowl.order.dto.requests;

import java.sql.Date;

public class CreateOrderRequest {

    private String orderAddress;
    private String orderZip;
    private String paymentId;

    public CreateOrderRequest() {
        super();
    }

    public CreateOrderRequest(String orderAddress, String orderZip, String paymentId) {
        this.orderAddress = orderAddress;
        this.orderZip = orderZip;
        this.paymentId = paymentId;
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderZip='" + orderZip + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
