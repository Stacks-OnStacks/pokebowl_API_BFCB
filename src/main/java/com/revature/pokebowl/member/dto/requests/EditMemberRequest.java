package com.revature.pokebowl.member.dto.requests;

import com.revature.pokebowl.util.web.dto.EditResourceRequests;

public class EditMemberRequest extends EditResourceRequests {

    private String fullName;
    private String username; //pulling the id from the parent class
    private String password;

    public EditMemberRequest() {
        super();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "EditMemberRequest{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}