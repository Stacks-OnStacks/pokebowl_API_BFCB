package com.revature.pokebowl.dish;

import com.revature.pokebowl.dish.dto.requests.CreateDishRequest;
import com.revature.pokebowl.dish.dto.requests.EditDishRequest;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
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
        if (dish == null) throw new InvalidUserInputException("Dish Id was not found in the database");
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
        if(dish.getDishId() == null || dish.getDishId().trim().equals("")) return false;
        if(dish.getDishName() == null || dish.getDishName().trim().equals("")) return false;
        if(dish.getDishCost() < 1) return false;
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

    public DishResponse update(EditDishRequest editDish) throws IOException {
        Dish foundDish = dishDao.findById(editDish.getId());
        if (foundDish == null) throw new InvalidUserInputException("Dish Id was not found in the database");
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        if (notNullOrEmpty.test(editDish.getDishName())) {
            if (!isDishNameAvailable(editDish.getDishName().toUpperCase())) throw new ResourcePersistanceException("Dish Name already exists, please enter a different name");
            foundDish.setDishName(editDish.getDishName().toUpperCase());
        }
        if (notNullOrEmpty.test(editDish.getDescription())) foundDish.setDescription(editDish.getDescription());
        if (editDish.getDishCost() > 0) {
            foundDish.setDishCost(editDish.getDishCost());
        } else {
            throw new InvalidUserInputException("Invalid cost (or no cost) entered, please try again");
        }
        foundDish.setVegetarian(editDish.isVegetarian());
        if (!dishDao.update(foundDish)) throw new ResourcePersistanceException("New dish failed to persist to the database");

        return new DishResponse(foundDish);
    }

    public boolean remove(String dishId) {
        if (!dishDao.delete(dishId)) throw new InvalidUserInputException("dish Id was not found in the database");
        return true;
    }
}
