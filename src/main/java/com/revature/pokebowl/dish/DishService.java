package com.revature.pokebowl.dish;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.dish.DishDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DishService {

    private final DishDao dishDao;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public DishService(DishDao dishDao){
        this.dishDao = dishDao;
    }
}
