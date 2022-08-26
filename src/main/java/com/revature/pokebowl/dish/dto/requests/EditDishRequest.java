package com.revature.pokebowl.dish.dto.requests;

import com.revature.pokebowl.util.web.dto.EditResourceRequests;

public class EditDishRequest extends EditResourceRequests {

    private String dishName;
    private int dishCost;
    private String description;
    private boolean isVegetarian;

    public EditDishRequest() {
        super();
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
        return "EditDishRequest{" +
                "dishName='" + dishName + '\'' +
                ", dishCost=" + dishCost +
                ", description='" + description + '\'' +
                ", isVegetarian=" + isVegetarian +
                '}';
    }
}
