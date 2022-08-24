package com.revature.pokebowl.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.dish.dto.requests.CreateDishRequest;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.order.dto.requests.CreateOrderRequest;
import com.revature.pokebowl.order.dto.responses.OrderResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import com.revature.pokebowl.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OrderServlet extends HttpServlet implements Authable {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger();

    public OrderServlet(OrderService orderService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkAuth(req,resp)) return;

        Member authMember = (Member) req.getSession().getAttribute("authMember");
        PrintWriter respWriter = resp.getWriter();
        String orderId = req.getParameter("orderId");

        if (orderId != null) {
            logger.info("orderId entered: {}",orderId);

            try {
                OrderResponse order = orderService.findByIdAndMember(orderId);
                String payload = objectMapper.writeValueAsString(order);
                respWriter.write(payload);
                resp.setStatus(200);
            } catch (InvalidUserInputException e) {
                logger.warn("Order id entered was not reflective of any order in the database related to the current authMember. orderId provided was: {}",orderId);
                respWriter.write(e.getMessage());
                resp.setStatus(404);
            }
        } else {
            logger.info("Obtaining all past orders for {}...",authMember.getUsername());
            List<OrderResponse> orders = orderService.readAllByMember();
            String payload = objectMapper.writeValueAsString(orders);
            respWriter.write(payload);
            resp.setStatus(200);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkAuth(req,resp)) return;
        PrintWriter respWriter = resp.getWriter();
        HttpSession httpSession = req.getSession(false);

        try {
            if (httpSession.getAttribute("currentOrder") == null) {
                CreateOrderRequest order = objectMapper.readValue(req.getInputStream(), CreateOrderRequest.class);
                logger.info("User has requested to create a new order {}", order);
                OrderResponse newOrder = orderService.startOrder(order);

                httpSession.setAttribute("currentOrder",newOrder);

                String payload = objectMapper.writeValueAsString(newOrder);
                respWriter.write(payload);
            } else {
                logger.info("User has requested to submit their new order");
                OrderResponse newOrder = orderService.submitOrder();
                String payload = objectMapper.writeValueAsString(newOrder);
                respWriter.write(payload);
            }
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
        if (checkAuth(req,resp)) return;
        PrintWriter respWriter = resp.getWriter();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkAuth(req,resp)) return;
        PrintWriter respWriter = resp.getWriter();

    }
}
