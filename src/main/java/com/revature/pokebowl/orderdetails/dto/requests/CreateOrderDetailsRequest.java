package com.revature.pokebowl.orderdetails.dto.requests;

public class CreateOrderDetailsRequest {

    private int quantity;
    private String comments;

    public CreateOrderDetailsRequest() {
        super();
    }

    public CreateOrderDetailsRequest(int quantity, String comments) {
        this.quantity = quantity;
        this.comments = comments;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "CreateOrderDetailsRequest{" +
                "quantity=" + quantity +
                ", comments='" + comments + '\'' +
                '}';
    }
}
