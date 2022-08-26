package com.revature.pokebowl.memberpayment;

import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.order.Order;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="member_payments")
public class Payment {

    @Id
    @Column(name="payment_id")
    private String paymentId;

    @Column(name="payment_name",nullable=false,unique=true)
    private String paymentName;

    @Column(name="balance",nullable=false)
    private int balance;

    @Column(name="exp_date",nullable=false)
    private Date expDate;

    @Column(name="ccv",nullable=false)
    private String ccv;

    @Column(name="zip_code",nullable=false)
    private String zipCode;

    @Column(name="provider",nullable=false)
    private String provider;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy="payment",cascade=CascadeType.ALL)
    private List<Order> orders = null;

    public Payment(String paymentId, String paymentName, int balance, Date expDate, String ccv, String zipCode, String provider) {
        this.paymentId = paymentId;
        this.paymentName = paymentName;
        this.balance = balance;
        this.expDate = expDate;
        this.ccv = ccv;
        this.zipCode = zipCode;
        this.provider = provider;
    }

    public Payment() {
        super();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentName='" + paymentName + '\'' +
                ", balance=" + balance +
                ", expDate=" + expDate +
                ", ccv='" + ccv + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
