package com.revature.pokebowl.member;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.pokebowl.memberpayment.MemberPayment;
import com.revature.pokebowl.order.Order;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="members")
public class Member {

    @Id
    @Column(name="member_id")
    private String memberId;

    @Column(name="username",nullable=false,unique=true)
    private String username;

    @Column(name="full_name",nullable=false)
    private String fullName;

    @Column(name="user_password",nullable=false)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String userPassword;

    @Column(name="dob",nullable=false)
    private Date dob;

    @Column(name="is_admin",nullable=false)
    private boolean isAdmin;

    @OneToMany(mappedBy="member",cascade=CascadeType.ALL)
    private List<MemberPayment> memberPaymentList;

    @OneToMany(mappedBy="member",cascade=CascadeType.ALL)
    private List<Order> orders;

    public Member(String memberId, String username, String fullName, String userPassword, Date dob, boolean isAdmin) {
        this.memberId = memberId;
        this.username = username;
        this.fullName = fullName;
        this.userPassword = userPassword;
        this.dob = dob;
        this.isAdmin = isAdmin;
    }

    public Member() {
        super();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<MemberPayment> getMemberPaymentList() {
        return memberPaymentList;
    }

    public void setMemberPaymentList(List<MemberPayment> memberPaymentList) {
        this.memberPaymentList = memberPaymentList;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", isAdmin=" + isAdmin +
                '}';
    }
}