package com.revature.pokebowl.memberpayment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;

public class PaymentServlet extends HttpServlet implements Authable {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger();

    public PaymentServlet(PaymentService paymentService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
    }
}
