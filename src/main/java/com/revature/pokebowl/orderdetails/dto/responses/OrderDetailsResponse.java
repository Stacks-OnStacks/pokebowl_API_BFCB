package com.revature.pokebowl.orderdetails.dto.responses;


public class OrderDetailsResponse {

    private String orderDetailsId;
    private int quantity;
    private String comments;

    public OrderDetailsResponse() {
        super();
    }

    public OrderDetailsResponse(String orderDetailsId, int quantity, String comments) {
        this.orderDetailsId = orderDetailsId;
        this.quantity = quantity;
        this.comments = comments;
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
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
        return "OrderDetailsResponse{" +
                "orderDetailsId='" + orderDetailsId + '\'' +
                ", quantity=" + quantity +
                ", comments='" + comments + '\'' +
                '}';
    }
}
