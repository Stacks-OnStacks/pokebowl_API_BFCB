package com.revature.pokebowl.orderdetails;
import com.revature.pokebowl.dish.DishService;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.order.OrderService;
import com.revature.pokebowl.orderdetails.OrderDetails;
import com.revature.pokebowl.orderdetails.dto.requests.CreateOrderDetailsRequest;
import com.revature.pokebowl.orderdetails.dto.responses.OrderDetailsResponse;
import com.revature.pokebowl.orderdetails.dto.requests.CreateOrderDetailsRequest;
import com.revature.pokebowl.orderdetails.dto.responses.OrderDetailsResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class OrderDetailsService {
    private final MemberService memberService;
    private final DishService dishService;
    private final OrderService orderService;

    private final OrderDetailsDao orderDetailsDao;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public OrderDetailsService(DishService dishService, OrderDetailsDao orderDetailsDao){
        this.memberService = memberService;
        this.orderDetailsDao = orderDetailsDao;

    }
    public OrderDetailsResponse registerOrderDetails(CreateOrderDetailsRequest newOrderDetailsRequest) throws InvalidUserInputException, ResourcePersistanceException {
        OrderDetails newOrderDetails = new OrderDetails();
        newOrderDetails.setComments(newOrderDetailsRequest.getComments());
        newOrderDetails.setQuantity(newOrderDetailsRequest.getQuantity());
        logger.info("OrderDetails registration service has begun with the provided: {}", newOrderDetails);
        orderDetailsDao.create(newOrderDetails);
        return new OrderDetailsResponse(newOrderDetails);
    }

}
