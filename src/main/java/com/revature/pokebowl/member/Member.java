package com.revature.pokebowl.member;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="members")
public class Member {

    @Id
    private String id;

    @Column(name="full_name",nullable=false)
    private String fullName;

    @Column(name="user_password",nullable=false)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String userPassword;

    @Column(name="dob",nullable=false)
    private Date dob;

    @Column(name="is_admin",nullable=false)
    private boolean isAdmin;

    public Member(String id, String fullName, String userPassword, Date dob, boolean isAdmin) {
        this.id = id;
        this.fullName = fullName;
        this.userPassword = userPassword;
        this.dob = dob;
        this.isAdmin = isAdmin;
    }

    public Member() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", dob=" + dob +
                ", isAdmin=" + isAdmin +
                '}';
    }
}