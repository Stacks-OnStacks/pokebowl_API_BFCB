package com.revature.pokebowl.orderdetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;

public class OrderDetailsServlet extends HttpServlet implements Authable {

    private final OrderDetailsService orderDetailsService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger();

    public OrderDetailsServlet(OrderDetailsService orderDetailsService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.orderDetailsService = orderDetailsService;
    }
}
