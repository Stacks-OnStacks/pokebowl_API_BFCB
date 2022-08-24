package com.revature.pokebowl.dish;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.dish.dto.requests.CreateDishRequest;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.member.dto.response.MemberResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
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

    public boolean isDishValid(Dish dish) {
        if(dish == null) return false;
        if(dish.getDishName() == null || dish.getDishName().trim().equals("")) return false;
        if(dish.getDishCost() < 0) return false;
        if(dish.getDescription() == null || dish.getDescription().trim().equals("")) return false;
        return true;
    }

    public boolean isDishNameAvailable(String dishName) {
        return dishDao.checkDishName(dishName);
    }

    public DishResponse createDish(CreateDishRequest createDishRequest) throws InvalidUserInputException {

        Dish newDish = new Dish();

        newDish.setDishId(UUID.randomUUID().toString());
        newDish.setDishCost(createDishRequest.getDishCost());
        newDish.setDishName(createDishRequest.getDishName().toUpperCase());
        newDish.setDescription(createDishRequest.getDescription());
        newDish.setVegetarian(createDishRequest.isVegetarian());

        logger.info("Dish Creation service has begun with the provided info: {}", newDish);
        if (!isDishValid(newDish)) {
            throw new InvalidUserInputException("User input was invalid, please try again");
        }

        if (!isDishNameAvailable(newDish.getDishName())) {
            throw new ResourcePersistanceException("Dish Name already exists, please enter a different name");
        }

        dishDao.create(newDish);
        return new DishResponse(newDish);

    }
}
