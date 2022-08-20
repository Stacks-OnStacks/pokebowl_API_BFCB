package com.revature.pokebowl.member.dto.requests;
import java.sql.Date;

public class NewRegistrationRequest {
    private String username;
    private String fullName;

    private String password;

    private Date dob;

    public NewRegistrationRequest() {
    }

    public NewRegistrationRequest(String username, String fullName, String password, Date dob) {
        this.username=username;
        this.fullName = fullName;
        this.password = password;
        this.dob = dob;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "NewRegistractionRequest{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}