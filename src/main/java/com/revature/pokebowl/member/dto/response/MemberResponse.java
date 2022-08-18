package com.revature.nabnak.member.dto.response;

import com.revature.pokebowl.member.Member;
import java.sql.Date;

public class MemberResponse {

    private String memberId;
    private String fullName;
    private String username;

    private Date dob;


    public MemberResponse() {
    }

    public MemberResponse(Member member){
        this.memberId = member.getMemberId();
        this.username = member.getUsername();
        this.fullName = member.getFullName();
        this.dob = member.getDob();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "MemberResponse{" +
                "memberId='" + memberId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", dob=" + dob +
                '}';
    }
}