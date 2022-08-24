package com.revature.pokebowl.order;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.memberpayment.PaymentService;
import com.revature.pokebowl.memberpayment.dto.requests.EditPaymentRequest;
import com.revature.pokebowl.order.dto.requests.CreateOrderRequest;
import com.revature.pokebowl.order.dto.requests.EditOrderRequest;
import com.revature.pokebowl.order.dto.responses.OrderResponse;
import com.revature.pokebowl.orderdetails.OrderDetails;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderService {

    private final OrderDao orderDao;
    private final PaymentService paymentService;
    private final MemberService memberService;
    private Order currentOrder;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public OrderService(MemberService memberService, PaymentService paymentService, OrderDao orderDao) {
        this.orderDao = orderDao;
        this.paymentService = paymentService;
        this.memberService = memberService;
    }

    public OrderResponse findByIdAndMember(String orderId) throws IOException {
        if (orderId.equals("current")) {
            if (currentOrder == null) throw new InvalidUserInputException("You must start a new order first");
            return new OrderResponse(currentOrder);
        }

        String sessionMemberId = memberService.getSessionMember().getMemberId();
        Order order = orderDao.findByIdAndMember(orderId,sessionMemberId);
        if (order == null) throw new InvalidUserInputException("Order Id associated with authMember was not found in the database");

        return new OrderResponse(order);
    }

    public List<OrderResponse> readAllByMember() {
        String sessionMemberId = memberService.getSessionMember().getMemberId();

        List<OrderResponse> orders = orderDao.findAllByMemberId(sessionMemberId)
                .stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
        return orders;
    }

    public OrderResponse startOrder(CreateOrderRequest order) throws IOException {
        currentOrder = new Order();

        currentOrder.setOrderId(UUID.randomUUID().toString());
        currentOrder.setOrderAddress(order.getOrderAddress());
        currentOrder.setOrderZip(order.getOrderZip());
        currentOrder.setPayment(paymentService.findById(order.getPaymentId()));
        currentOrder.setMember(memberService.getSessionMember());

        logger.info("Order Creation service has begun with the provided: {}",currentOrder);
        if (!isOrderValid(true)) {
            throw new InvalidUserInputException("User input was invalid");
        }

        return new OrderResponse(currentOrder);
    }

    public OrderResponse submitOrder() throws IOException {
        List<OrderDetails> orderDetailsList = currentOrder.getOrderDetailsList();
        if (orderDetailsList == null) throw new InvalidUserInputException("Nothing has been added to this order yet, cannot submit the order");

        int amount = 0;
        for (OrderDetails orderDetails: orderDetailsList) {
            amount += (orderDetails.getQuantity() * orderDetails.getDish().getDishCost());
        }

        int newBalance = currentOrder.getPayment().getBalance() - amount;
        if (newBalance < 0) throw new IOException("Insufficient Funds, please add more funds or remove some items from your order");

        EditPaymentRequest editPayment = new EditPaymentRequest();
        editPayment.setPaymentId(currentOrder.getPayment().getPaymentId());
        editPayment.setBalance(newBalance);
        paymentService.update(editPayment);

        currentOrder.setAmount(amount);
        currentOrder.setOrderDate(new Date(System.currentTimeMillis()));

        logger.info("Order Creation service is trying to submit the provided order: {}",currentOrder);
        if (!isOrderValid(false)) {
            throw new InvalidUserInputException("Submitted Order was invalid");
        }

        Order order = orderDao.create(currentOrder);
        if (order == null) throw new ResourcePersistanceException("Order could not be persisted to the database");
        nullifyCurrentOrder();

        return new OrderResponse(order);

    }

    public boolean isOrderValid(boolean firstCheck) {
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        if (currentOrder == null) return false;
        if (currentOrder.getMember() == null) return false;
        if (currentOrder.getPayment() == null) return false;
        if (!notNullOrEmpty.test(currentOrder.getOrderId())) return false;
        if (!notNullOrEmpty.test(currentOrder.getOrderAddress())) return false;
        if (!notNullOrEmpty.test(currentOrder.getOrderZip())) return false;
        if (!firstCheck) {
            if (currentOrder.getOrderDate() == null) return false;
            if (currentOrder.getAmount() < 1) return false;
        }
        return true;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void nullifyCurrentOrder() {
        currentOrder = null;
    }

    public OrderResponse update(EditOrderRequest editOrder) throws IOException {
        if (currentOrder == null) throw new IOException("No current order in progress, create a new order first");
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");

        if (notNullOrEmpty.test(editOrder.getOrderAddress())) {
            currentOrder.setOrderAddress(editOrder.getOrderAddress());
        }
        if (notNullOrEmpty.test(editOrder.getOrderZip())) {
            currentOrder.setOrderZip(editOrder.getOrderZip());
        }
        if (notNullOrEmpty.test(editOrder.getPaymentId())) {
            currentOrder.setPayment(paymentService.findById(editOrder.getPaymentId()));
        }

        return new OrderResponse(currentOrder);
    }
}