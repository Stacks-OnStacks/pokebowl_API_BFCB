package com.revature.pokebowl.memberpayment.dto.responses;

import com.revature.pokebowl.memberpayment.Payment;

import java.sql.Date;

public class PaymentResponse {

    private String paymentId;
    private String paymentName;
    private int balance;
    private Date expDate;
    private String ccv;
    private String zipCode;
    private String provider;

    private String memberId;

    public PaymentResponse() {
        super();
    }

    public PaymentResponse(String paymentId, String paymentName, int balance, Date expDate, String ccv, String zipCode, String provider, String memberId) {
        this.paymentId = paymentId;
        this.paymentName = paymentName;
        this.balance = balance;
        this.expDate = expDate;
        this.ccv = ccv;
        this.zipCode = zipCode;
        this.provider = provider;
        this.memberId = memberId;
    }
    public PaymentResponse(Payment payment) {
        this.paymentId = payment.getPaymentId();
        this.paymentName = payment.getPaymentName();
        this.balance = payment.getBalance();
        this.expDate = payment.getExpDate();
        this.ccv = payment.getCcv();
        this.zipCode = payment.getZipCode();
        this.provider = payment.getProvider();
        this.memberId = payment.getMember().getMemberId();
        //set all this.attribute= paymment.getAttribute()
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentName='" + paymentName + '\'' +
                ", balance=" + balance +
                ", expDate=" + expDate +
                ", ccv='" + ccv + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", provider='" + provider + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
