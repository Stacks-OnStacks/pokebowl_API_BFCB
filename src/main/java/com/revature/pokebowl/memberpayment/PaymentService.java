package com.revature.pokebowl.memberpayment;

import com.revature.pokebowl.dish.DishDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentService {

    private final PaymentDao paymentDao;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public PaymentService(PaymentDao paymentDao){
        this.paymentDao = paymentDao;
    }
}
