package com.revature.pokebowl.order;

import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.memberpayment.Payment;
import com.revature.pokebowl.orderdetails.OrderDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="payment_id")
    private Payment payment;

    @OneToMany(mappedBy="order")
    private Set<OrderDetails> orderDetailsSet;

    public Order(String orderId, int amount, Date orderDate, String orderAddress, String orderZip) {
        this.orderId = orderId;
        this.amount = amount;
        this.orderDate = orderDate;
        this.orderAddress = orderAddress;
        this.orderZip = orderZip;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderDetails> getOrderDetailsSet() {
        return orderDetailsSet;
    }

    public void setOrderDetailsSet(Set<OrderDetails> orderDetailsSet) {
        this.orderDetailsSet = orderDetailsSet;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", orderDate=" + orderDate +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderZip='" + orderZip + '\'' +
                '}';
    }
}
