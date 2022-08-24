package com.revature.pokebowl.order.dto.responses;

import com.revature.pokebowl.order.Order;

import java.sql.Date;

public class OrderResponse {

    private String orderId;
    private int amount;
    private Date orderDate;
    private String orderAddress;
    private String orderZip;

    private String memberId;
    private String paymentId;

    public OrderResponse() {
        super();
    }

    public OrderResponse(Order order) {
        this.orderId = order.getOrderId();
        this.amount = order.getAmount();
        this.orderDate = order.getOrderDate();
        this.orderAddress = order.getOrderAddress();
        this.orderZip = order.getOrderZip();
        this.memberId = order.getMember().getMemberId();
        this.paymentId = order.getPayment().getPaymentId();
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
        return "OrderResponse{" +
                "orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", orderDate=" + orderDate +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderZip='" + orderZip + '\'' +
                ", memberId='" + memberId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
