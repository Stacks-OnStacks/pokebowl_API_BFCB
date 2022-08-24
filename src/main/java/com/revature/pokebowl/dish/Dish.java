package com.revature.pokebowl.dish;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.pokebowl.orderdetails.OrderDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="dishes")
public class Dish {

    @Id
    @Column(name="dish_id")
    private String dishId;

    @Column(name="dish_name",nullable=false,unique=true)
    private String dishName;

    @Column(name="dish_cost",nullable=false)
    private int dishCost;

    @Column(name="description",nullable=false)
    private String description;

    @Column(name="is_vegetarian",nullable=false)
    private boolean isVegetarian;

    @OneToMany(mappedBy="dish",cascade=CascadeType.ALL)
    private List<OrderDetails> orderDetailsList;

    public Dish(String dishId, String dishName, int dishCost, String description, boolean isVegetarian) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishCost = dishCost;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }

    public Dish() {
        super();
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishCost() {
        return dishCost;
    }

    public void setDishCost(int dishCost) {
        this.dishCost = dishCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId='" + dishId + '\'' +
                ", dishName='" + dishName + '\'' +
                ", dishCost=" + dishCost +
                ", description='" + description + '\'' +
                ", isVegetarian=" + isVegetarian +
                '}';
    }
}
