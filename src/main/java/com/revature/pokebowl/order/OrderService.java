package com.revature.pokebowl.order;

import com.revature.pokebowl.dish.DishDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderService {

    private final OrderDao orderDao;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public OrderService(OrderDao orderDao){
        this.orderDao = orderDao;
    }
}
