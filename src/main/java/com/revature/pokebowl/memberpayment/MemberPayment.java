package com.revature.pokebowl.memberpayment;

import com.revature.pokebowl.member.Member;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="member_payment")
public class MemberPayment {

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

    @Column(name="customer_username",nullable=false)
    private String customerUserName;

    public MemberPayment(String paymentId, int balance, Date expDate, String ccv, String zipCode, String provider, String customerUserName) {
        this.paymentId = paymentId;
        this.balance = balance;
        this.expDate = expDate;
        this.ccv = ccv;
        this.zipCode = zipCode;
        this.provider = provider;
        this.customerUserName = customerUserName;
    }
}
