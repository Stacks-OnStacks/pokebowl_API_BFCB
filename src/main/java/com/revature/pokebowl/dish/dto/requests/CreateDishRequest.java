package com.revature.pokebowl.dish.dto.requests;


public class CreateDishRequest {

    private String dishName;
    private int dishCost;
    private String description;
    private boolean isVegetarian;

    public CreateDishRequest() {
        super();
    }

    public CreateDishRequest(String dishName, int dishCost, String description, boolean isVegetarian) {
        this.dishName = dishName;
        this.dishCost = dishCost;
        this.description = description;
        this.isVegetarian = isVegetarian;
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
        return "CreateDishRequest{" +
                "dishName='" + dishName + '\'' +
                ", dishCost=" + dishCost +
                ", description='" + description + '\'' +
                ", isVegetarian=" + isVegetarian +
                '}';
    }
}
