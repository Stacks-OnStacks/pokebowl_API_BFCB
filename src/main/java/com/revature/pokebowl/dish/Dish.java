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

    @Column(name="dish_name",nullable=false)
    private String dishName;

    @Column(name="dish_cost",nullable=false)
    private int dishCost;

    @Column(name="description",nullable=false)
    private String description;

    @Column(name="is_admin",nullable=false)
    private boolean isVegetarian;

    public Dish(String id, String dishName, int dishCost, String description, boolean isVegetarian) {
        this.id = id;
        this.dishName = dishName;
        this.dishCost = dishCost;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }

    public Dish() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", dishName='" + dishName + '\'' +
                ", dishCost=" + dishCost +
                ", description='" + description + '\'' +
                ", isVegetarian=" + isVegetarian +
                '}';
    }
}
