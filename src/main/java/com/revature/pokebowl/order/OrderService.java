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

    public boolean startNewOrder() {
        if (currentOrder != null) return false;
        currentOrder = new Order();
        return true;
    }
    public OrderResponse postFinishedOrder() {
        return null;
    }

    public OrderResponse startOrder(CreateOrderRequest order) {
        return null;
    }
    public OrderResponse submitOrder() {
        return null;
    }
}
