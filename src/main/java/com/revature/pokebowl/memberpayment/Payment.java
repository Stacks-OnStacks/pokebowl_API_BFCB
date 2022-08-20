package com.revature.pokebowl.memberpayment;

import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.order.Order;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="member_payments")
public class Payment {

    @Id
    @Column(name="payment_id")
    private String paymentId;

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

    @OneToMany(mappedBy="memberPayment",cascade=CascadeType.ALL)
    private Set<Order> orderSet;

    public Payment(String paymentId, int balance, Date expDate, String ccv, String zipCode, String provider, String customerUserName) {
        this.paymentId = paymentId;
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

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    @Override
    public String toString() {
        return "MemberPayment{" +
                "paymentId='" + paymentId + '\'' +
                ", balance=" + balance +
                ", expDate=" + expDate +
                ", ccv='" + ccv + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
