package com.revature.pokebowl.dish.dto.responses;

import com.revature.pokebowl.dish.Dish;


public class DishResponse {

    private String dishId;
    private String dishName;
    private int dishCost;
    private String description;
    private boolean isVegetarian;

    public DishResponse() {
        super();
    }

    public DishResponse(Dish dish) {
        this.dishId = dish.getDishId();
        this.dishName = dish.getDishName();
        this.dishCost = dish.getDishCost();
        this.description = dish.getDescription();
        this.isVegetarian = dish.isVegetarian();
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

    @Override
    public String toString() {
        return "DishResponse{" +
                "dishId='" + dishId + '\'' +
                ", dishName='" + dishName + '\'' +
                ", dishCost=" + dishCost +
                ", description='" + description + '\'' +
                ", isVegetarian=" + isVegetarian +
                '}';
    }
}
