package com.revature.pokebowl.orderdetails.dto.requests;

import com.revature.pokebowl.util.web.dto.EditResourceRequests;

public class EditOrderDetailsRequest extends EditResourceRequests {

    private int quantity;
    private String comments;

    public EditOrderDetailsRequest() {
        super();
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
        return "EditOrderDetailsRequest{" +
                "quantity=" + quantity +
                ", comments='" + comments + '\'' +
                '}';
    }
}
