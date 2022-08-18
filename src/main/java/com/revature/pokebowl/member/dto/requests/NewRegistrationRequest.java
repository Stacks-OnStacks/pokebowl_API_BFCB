package com.revature.pokebowl.member.dto.requests;
import java.sql.Date;

public class NewRegistrationRequest {

    private String fullName;

    private String email;

    private String password;

    private Date dob;

    public NewRegistrationRequest() {
    }

    public NewRegistrationRequest(String fullName, String email, String password, Date dob) {
        this.fullName = fullName;
        this.email = email;
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
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}