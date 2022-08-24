package com.revature.pokebowl.order;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.memberpayment.PaymentService;
import com.revature.pokebowl.order.dto.requests.CreateOrderRequest;
import com.revature.pokebowl.order.dto.responses.OrderResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
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
        Order newOrder = new Order();

        newOrder.setOrderId(UUID.randomUUID().toString());
        newOrder.setOrderAddress(order.getOrderAddress());
        newOrder.setOrderZip(order.getOrderZip());
        newOrder.setPayment(paymentService.findById(order.getPaymentId()));
        newOrder.setMember(memberService.getSessionMember());
        return new OrderResponse(newOrder);
    }

}
