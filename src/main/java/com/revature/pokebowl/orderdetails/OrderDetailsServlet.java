package com.revature.pokebowl.orderdetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.memberpayment.dto.requests.CreatePaymentRequest;
import com.revature.pokebowl.memberpayment.dto.responses.PaymentResponse;
import com.revature.pokebowl.orderdetails.dto.requests.CreateOrderDetailsRequest;
import com.revature.pokebowl.orderdetails.dto.responses.OrderDetailsResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import com.revature.pokebowl.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OrderDetailsServlet extends HttpServlet implements Authable {

    private final OrderDetailsService orderDetailsService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger();

    public OrderDetailsServlet(OrderDetailsService orderDetailsService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.orderDetailsService = orderDetailsService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkAuth(req,resp)) return;
        String orderDetailsId = req.getParameter("orderDetailsId");
        String orderId = req.getParameter("ordersId");
        Member authMember = (Member) req.getSession().getAttribute("authMember"); // cast the returned object to a member
        if(orderDetailsId!=null && orderId!=null){
            logger.info("orderDetailsId entered: {}", orderDetailsId);
            logger.info("orderId entered: {}", orderId);
            try {
                OrderDetailsResponse orderDetailsResp = orderDetailsService.findByIdAndOrder(orderDetailsId, orderId);
                if (orderDetailsResp== null) throw new InvalidUserInputException("entered orderDetailsId or orderId was not found in the database");
                String payloadID = objectMapper.writeValueAsString(orderDetailsResp);
                resp.getWriter().write(payloadID);
                resp.setStatus(200);
            }
            catch (InvalidUserInputException e){
                logger.warn("OrderDetailsId or OrderId information entered was not reflective of any payment in the database. OrderDetailsId provided was: {},{}", orderDetailsId,orderId);
                resp.getWriter().write(e.getMessage());
                resp.setStatus(404);
            }
        }
        else if(orderDetailsId!=null){
            logger.info("orderDetailsId entered: {}", orderDetailsId);
            try {
                OrderDetailsResponse orderDetailsResp = orderDetailsService.findById(orderDetailsId);
                if (orderDetailsResp== null) throw new InvalidUserInputException("entered orderDetailsId was not found in the database");
                String payloadID = objectMapper.writeValueAsString(orderDetailsResp);
                resp.getWriter().write(payloadID);
                resp.setStatus(200);
            }
            catch (InvalidUserInputException e){
                logger.warn("OrderDetailsId information entered was not reflective of any OrderDetailsId in the database. OrderDetailsId provided was: {}", orderDetailsId);
                resp.getWriter().write(e.getMessage());
                resp.setStatus(404);
            }
        }
        else if(orderId!=null){
            logger.info("orderId entered: {}", orderId);
            try {
                List<OrderDetailsResponse> orderDetailsResp = orderDetailsService.findAllByOrderId(orderId);
                if (orderDetailsResp== null) throw new InvalidUserInputException("entered orderId was not found in the database");
                String payloadID = objectMapper.writeValueAsString(orderDetailsResp);
                resp.getWriter().write(payloadID);
                resp.setStatus(200);
            }
            catch (InvalidUserInputException e){
                logger.warn("OrderId information entered was not reflective of any orderId in the database. OrderId provided was: {}", orderId);
                resp.getWriter().write(e.getMessage());
                resp.setStatus(404);
            }
        }
        else{
            logger.info("no orderDetails entered, getting all orderDetails");
            List<OrderDetailsResponse> orderDetails = orderDetailsService.readAllCurrentOrder();
            String payload = objectMapper.writeValueAsString(orderDetails); // mapper parsing from Java Object to JSON
            resp.getWriter().write(payload);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkAuth(req,resp)) return;
        PrintWriter respWriter = resp.getWriter();
        CreateOrderDetailsRequest orderDetail = objectMapper.readValue(req.getInputStream(), CreateOrderDetailsRequest.class);
        try{
            logger.info("User has request to add the following to the database {}", orderDetail);
            OrderDetailsResponse newOrderDetails = orderDetailsService.registerOrderDetails(orderDetail);
            String payload = objectMapper.writeValueAsString(orderDetail);
            respWriter.write(payload);
            resp.setStatus(201);

        }catch (InvalidUserInputException | ResourcePersistanceException e){
            logger.warn("Exception thrown when trying to persist. Message from exception: {}", e.getMessage());
            respWriter.write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e) {
            logger.error("Something unexpected happened and this exception was thrown: {} with message: {}", e.getClass().getName(), e.getMessage());
            respWriter.write(e.getMessage() + " " + e.getClass().getName());
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
