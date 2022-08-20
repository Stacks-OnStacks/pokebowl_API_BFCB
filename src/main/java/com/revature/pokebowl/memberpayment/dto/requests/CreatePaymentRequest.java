package com.revature.pokebowl.memberpayment.dto.requests;

import java.sql.Date;

public class CreatePaymentRequest {

    private int balance;
    private Date expDate;
    private String ccv;
    private String zipCode;
    private String provider;

    private String memberId;

    public CreatePaymentRequest() {
        super();
    }

    public CreatePaymentRequest(int balance, Date expDate, String ccv, String zipCode, String provider, String memberId) {
        this.balance = balance;
        this.expDate = expDate;
        this.ccv = ccv;
        this.zipCode = zipCode;
        this.provider = provider;
        this.memberId = memberId;
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

    @Override
    public String toString() {
        return "CreatePaymentRequest{" +
                "balance=" + balance +
                ", expDate=" + expDate +
                ", ccv='" + ccv + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", provider='" + provider + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
