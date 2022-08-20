package com.revature.pokebowl.orderdetails;

import com.revature.pokebowl.dish.DishDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDetailsService {

    private final OrderDetailsDao orderDetailsDao;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public OrderDetailsService(OrderDetailsDao orderDetailsDao){
        this.orderDetailsDao = orderDetailsDao;
    }
}
