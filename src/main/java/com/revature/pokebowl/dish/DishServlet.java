package com.revature.pokebowl.dish;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.dish.dto.requests.CreateDishRequest;
import com.revature.pokebowl.dish.dto.requests.EditDishRequest;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.member.dto.requests.EditMemberRequest;
import com.revature.pokebowl.member.dto.requests.NewRegistrationRequest;
import com.revature.pokebowl.member.dto.response.MemberResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import com.revature.pokebowl.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DishServlet extends HttpServlet implements Authable {

    private final DishService dishService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger();

    public DishServlet(DishService dishService, ObjectMapper objectMapper) {
        this.dishService = dishService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter respWriter = resp.getWriter();
        String dishId = req.getParameter("dishId");

        if (dishId != null) {
            logger.info("dishId entered: {}",dishId);

            try {
                DishResponse dish = dishService.findById(dishId);
                String payload = objectMapper.writeValueAsString(dish);
                respWriter.write(payload);
                resp.setStatus(200);
            } catch (InvalidUserInputException e) {
                logger.warn("Dish id entered was not reflective of any dish in the database. dishId provided was: {}",dishId);
                respWriter.write(e.getMessage());
                resp.setStatus(404);
            }
        } else {
            logger.info("Obtaining all dishes...");
            List<DishResponse> dishes = dishService.readAll();
            String payload = objectMapper.writeValueAsString(dishes);
            respWriter.write(payload);
            resp.setStatus(200);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!checkAdmin(req, resp)) return;

        PrintWriter respWriter = resp.getWriter();
        CreateDishRequest dish = objectMapper.readValue(req.getInputStream(), CreateDishRequest.class);

        try {
            logger.info("Admin has requested to add the following dish to the database {}", dish);
            DishResponse newDish = dishService.createDish(dish);
            String payload = objectMapper.writeValueAsString(newDish);
            respWriter.write(payload);
            resp.setStatus(201);
        } catch (InvalidUserInputException | ResourcePersistanceException e){
            logger.warn("Exception thrown when trying to persist. Message from exception: {}", e.getMessage());
            respWriter.write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            logger.error("Something unexpected happened and this exception was thrown: {} with message: {}", e.getClass().getName(), e.getMessage());
            respWriter.write(e.getMessage() + " " + e.getClass().getName());
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAdmin(req, resp)) return;

        PrintWriter respWriter = resp.getWriter();
        EditDishRequest editDish = objectMapper.readValue(req.getInputStream(), EditDishRequest.class);

        try {
            DishResponse dish = dishService.update(editDish);

            logger.info("Successfully updated dish: {}",dish);
            String payload = objectMapper.writeValueAsString(dish);
            respWriter.write(payload);
            resp.setStatus(200);
        } catch (InvalidUserInputException | ResourcePersistanceException e) {
            logger.warn("Exception thrown when trying to persist. Message from exception: {}", e.getMessage());
            respWriter.write(e.getMessage());
            resp.setStatus(404);
        }  catch (Exception e) {
            logger.error("Something unexpected happened and this exception was thrown: {} with message: {}", e.getClass().getName(), e.getMessage());
            respWriter.write(e.getMessage() + " " + e.getClass().getName());
            e.printStackTrace();
            resp.setStatus(500);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAdmin(req, resp)) return;

        PrintWriter respWriter = resp.getWriter();
        String dishId = req.getParameter("dishId");

        if(dishId != null){
            logger.info("dishId entered: {}",dishId);

            try {
                dishService.remove(dishId);
                respWriter.write(String.format("Dish with id '%s' has been deleted",dishId));
                resp.setStatus(200);
            } catch (InvalidUserInputException e) {
                logger.warn("dishId entered was not reflective of any dish in the database. dishId provided was: {}",dishId);
                respWriter.write(e.getMessage());
                resp.setStatus(404);
            }
        } else {
            logger.warn("No dishId query for doDelete");
            respWriter.write("This request requires a dishId parameter in the path ?dishId=DISH_ID");
            resp.setStatus(400);
        }
    }
}
