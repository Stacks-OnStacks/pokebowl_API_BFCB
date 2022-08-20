package com.revature.pokebowl.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;

public class OrderServlet extends HttpServlet implements Authable {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger();

    public OrderServlet(OrderService orderService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }
}
