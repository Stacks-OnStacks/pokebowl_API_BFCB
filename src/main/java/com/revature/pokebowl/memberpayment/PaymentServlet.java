package com.revature.pokebowl.memberpayment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.memberpayment.Payment;
import com.revature.pokebowl.memberpayment.dto.requests.EditPaymentRequest;
import com.revature.pokebowl.memberpayment.dto.requests.CreatePaymentRequest;
import com.revature.pokebowl.memberpayment.dto.responses.PaymentResponse;
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

public class PaymentServlet extends HttpServlet implements Authable {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger();

    public PaymentServlet(PaymentService paymentService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String paymentName = req.getParameter("payment_name");
        Payment authMember = (Payment) req.getSession().getAttribute("authMember"); // cast the returned object to a member

        if(paymentName != null) {
            logger.info("username entered: {}", paymentName);
            try {
                PaymentResponse payment = paymentService.findByPaymentName(paymentName);

                if (payment == null) throw new InvalidUserInputException("entered paymentName was not found in the database");

                String payloadID = objectMapper.writeValueAsString(payment);

                resp.getWriter().write(payloadID);
            } catch (InvalidUserInputException e){
                logger.warn("Payment information entered was not reflective of any payment in the database. paymentName provided was: {}", paymentName);
                resp.getWriter().write(e.getMessage());
                resp.setStatus(404);
            }
        } else {
            logger.info("no payment entered, getting all payments");
            List<PaymentResponse> payments = paymentService.readAll();
            String payload = objectMapper.writeValueAsString(payments); // mapper parsing from Java Object to JSON
            resp.getWriter().write(payload);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // DO NOT DO LOGIN INSIDE OF YOUR MEMBER SERVLET
//        LoginCreds loginCreds = objectMapper.readValue(req.getInputStream(), LoginCreds.class); // this provides the body from the request as a JSON, leveraging Reflections
//
//        Member member = memberService.login(loginCreds.getmember_id(), loginCreds.getPassword());
//
//        resp.getWriter().write("Welcome back to pokebowl " + member.getFullName());
        PrintWriter respWriter = resp.getWriter();
        CreatePaymentRequest payment = objectMapper.readValue(req.getInputStream(), CreatePaymentRequest.class);
        try {
            logger.info("User has request to add the following to the database {}", payment);
            PaymentResponse newPayment = paymentService.registerPayment(payment);
            String payload = objectMapper.writeValueAsString(newPayment);
            respWriter.write(payload);
            resp.setStatus(201);
        } catch (InvalidUserInputException | ResourcePersistanceException e){
            logger.warn("Exception thrown when trying to persist. Message from exception: {}", e.getMessage());
            respWriter.write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            logger.error("Something unexpected happened and this exception was thrown: {} with message: {}", e.getClass().getName(), e.getMessage());
            respWriter.write(e.getMessage() + " " + e.getClass().getName());
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String paymentName = req.getParameter("payment_name");
            EditPaymentRequest editPayment = objectMapper.readValue(req.getInputStream(), EditPaymentRequest.class);
            if(paymentName != null) {
                logger.info("paymentName entered: {}", paymentName);
                try {
                    PaymentResponse payment = paymentService.findByPaymentName(paymentName);
                    if (payment == null) throw new InvalidUserInputException("entered paymentName was not found in the database");
                    editPayment.setPaymentId(payment.getPaymentId());
                    paymentService.update(editPayment);// editPayment is a CreatePaymentRequest type of object, it
                    logger.info("Successfully updated member: {}",editPayment.getPaymentName());
                    String payload = objectMapper.writeValueAsString(editPayment);
                    resp.getWriter().write(payload);
                    resp.setStatus(200);// 200 means everything is OK, successfully updated payment method

                } catch (InvalidUserInputException e){
                    logger.warn("User information entered was not reflective of any member in the database. username provided was: {}", paymentName);
                    resp.getWriter().write(e.getMessage());
                    resp.setStatus(404);
                }
            } else {
                throw new InvalidUserInputException("Cannot update member, username is null");
            }
        } catch (InvalidUserInputException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage() + " " + e.getClass().getName());
            resp.setStatus(500);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!checkAdmin(req, resp)) return;
        String paymentName = req.getParameter("payment_name");
        if(paymentName != null){
            paymentService.remove(paymentName);
            resp.getWriter().write(String.format("Payment: %s has been deleted",paymentName));
        } else {
            resp.getWriter().write("This request requires an paymentName parameter in the path ?payment_name=YOUR-PAYMENT_NAME");
            resp.setStatus(400);
        }
    }
}
