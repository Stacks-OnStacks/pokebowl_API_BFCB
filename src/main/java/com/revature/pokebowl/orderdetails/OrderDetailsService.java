package com.revature.pokebowl.orderdetails;
import com.revature.pokebowl.dish.DishService;
import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.memberpayment.Payment;
import com.revature.pokebowl.memberpayment.dto.responses.PaymentResponse;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderDetailsService {
    private final DishService dishService;
    private final OrderService orderService;

    private final OrderDetailsDao orderDetailsDao;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public OrderDetailsService(DishService dishService, OrderService orderService, OrderDetailsDao orderDetailsDao){
        this.dishService = dishService;
        this.orderService = orderService;
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
    public List<OrderDetailsResponse> readAll(){
        // Streams are a form of functional programming this is form a declarative programming
        List<OrderDetailsResponse> orderDetails = orderDetailsDao.findAll()
                .stream()//this reads through each value inside of the collection (aka our List)
                //.map(member -> new MemberResponse(member))
                // this is leveraging (::) which is known as the method reference operator, it's taking the method from MemberReponse and applying to all objects in the stream
                .map(OrderDetailsResponse::new)
                .collect(Collectors.toList());
        return orderDetails;
    }
    public OrderDetailsResponse findById(String orderDetailsId){
        OrderDetails orderDetails = orderDetailsDao.findById(orderDetailsId);
        if (orderDetails == null) return null;
        OrderDetailsResponse paymentResponse = new OrderDetailsResponse(orderDetails);
        return paymentResponse;
    }
    public boolean remove(String orderDetailsId){
        return orderDetailsDao.delete(orderDetailsId);
    }
}
