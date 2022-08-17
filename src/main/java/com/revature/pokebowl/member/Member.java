package com.revature.pokebowl.member;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="members")
public class Member {

    @Id
    private String id;

    @Column(name="full_name",nullable=false)
    private String full_name;

    @Column(name="user_password",nullable=false)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String user_password;

    @Column(name="dob",nullable=false)
    private Date dob;

    @Column(name="is_admin",nullable=false)
    private boolean is_admin;

    public Member(String id, String full_name, String user_password, Date dob, boolean is_admin) {
        this.id = id;
        this.full_name = full_name;
        this.user_password = user_password;
        this.dob = dob;
        this.is_admin = is_admin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public Date getDob() {
        return dob;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", dob=" + dob +
                ", is_admin=" + is_admin +
                '}';
    }
}