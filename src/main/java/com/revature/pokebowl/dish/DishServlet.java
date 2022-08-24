package com.revature.pokebowl.dish;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String dishName = req.getParameter("dishName");

        if (dishName != null) {
            logger.info("dishName entered: {}",dishName);

            try {
                DishResponse dish = dishService.findById(dishName);
                String payload = objectMapper.writeValueAsString(dish);
                resp.getWriter().write(payload);
                resp.setStatus(200);
            } catch (InvalidUserInputException e) {
                logger.warn("Dish id entered was not reflective of any member in the database. dishId provided was: {}",dishName);
                resp.getWriter().write(e.getMessage());
                resp.setStatus(404);
            }
        } else {
            logger.info("Obtaining all dishes...");
            List<DishResponse> dishes = dishService.readAll();
            String payload = objectMapper.writeValueAsString(dishes);
            resp.getWriter().write(payload);
            resp.setStatus(200);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
