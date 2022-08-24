package com.revature.pokebowl.dish;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DishService {

    private final DishDao dishDao;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public DishService(DishDao dishDao){
        this.dishDao = dishDao;
    }

    public DishResponse findById(String dishId) throws IOException {
        Dish dish = dishDao.findById(dishId);
        if (dish == null) throw new InvalidUserInputException("Dish Name was not found in the database");
        return new DishResponse(dish);
    }

    public List<DishResponse> readAll() {

        List<DishResponse> dishes = dishDao.findAll()
                .stream()
                .map(DishResponse::new)
                .collect(Collectors.toList());
        return dishes;
    }
}
