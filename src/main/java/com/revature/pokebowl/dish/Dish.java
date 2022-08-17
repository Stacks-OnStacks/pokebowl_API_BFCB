package com.revature.pokebowl.dish;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="dishes")
public class Dish {

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

}
