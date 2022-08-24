package com.revature.pokebowl.memberpayment.dto.requests;

import java.sql.Date;

public class CreatePaymentRequest {

    private String paymentName;
    private int balance;
    private Date expDate;
    private String ccv;
    private String zipCode;
    private String provider;

    private String paymentId;

    public CreatePaymentRequest() {
        super();
    }

    public CreatePaymentRequest(String paymentName, int balance, Date expDate, String ccv, String zipCode, String provider, String paymentId) {
        this.paymentName = paymentName;
        this.balance = balance;
        this.expDate = expDate;
        this.ccv = ccv;
        this.zipCode = zipCode;
        this.provider = provider;
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String memberId) {
        this.paymentId = memberId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    @Override
    public String toString() {
        return "CreatePaymentRequest{" +
                "paymentName='" + paymentName + '\'' +
                ", balance=" + balance +
                ", expDate=" + expDate +
                ", ccv='" + ccv + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", provider='" + provider + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
