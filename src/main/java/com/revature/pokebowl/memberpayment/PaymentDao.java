package com.revature.pokebowl.memberpayment;

import com.revature.pokebowl.util.interfaces.Crudable;

import java.util.List;

public class PaymentDao implements Crudable<Payment> {

    @Override
    public Payment create(Payment newObject) {
        return null;
    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public Payment findById(String id) {
        return null;
    }

    @Override
    public boolean update(Payment updatedObject) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
