package com.revature.pokebowl.orderdetails;
import com.revature.pokebowl.dish.DishService;
import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.orderdetails.OrderDetails;
import com.revature.pokebowl.orderdetails.dto.requests.EditOrderDetailsRequest;
import com.revature.pokebowl.orderdetails.dto.responses.OrderDetailsResponse;
import com.revature.pokebowl.order.OrderService;
import com.revature.pokebowl.orderdetails.OrderDetails;
import com.revature.pokebowl.orderdetails.dto.requests.CreateOrderDetailsRequest;
import com.revature.pokebowl.orderdetails.dto.requests.EditOrderDetailsRequest;
import com.revature.pokebowl.orderdetails.dto.responses.OrderDetailsResponse;
import com.revature.pokebowl.orderdetails.dto.requests.CreateOrderDetailsRequest;
import com.revature.pokebowl.orderdetails.dto.responses.OrderDetailsResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
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
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse(orderDetails);
        return orderDetailsResponse;
    }
    public List<OrderDetailsResponse> findAllByOrderId(String orderId){
        List<OrderDetailsResponse> orderDetails = orderDetailsDao.findAllByOrderId(orderId).stream().map(OrderDetailsResponse::new).collect(Collectors.toList());
        return orderDetails;
    }
    public OrderDetailsResponse findByIdAndOrder(String orderDetailsId,String orderId){
        List<OrderDetailsResponse> orderDetails = orderDetailsDao.findByIdAndOrder(orderId).stream().map(OrderDetailsResponse::new).collect(Collectors.toList());
        return orderDetails;
    }


    public boolean remove(String orderDetailsId){
        return orderDetailsDao.delete(orderDetailsId);
    }

    public boolean update(EditOrderDetailsRequest editOrderDetails) throws InvalidUserInputException{
        System.out.println("Inside update OrderDetails");
        OrderDetails foundOrderDetails = orderDetailsDao.findById(editOrderDetails.getId());
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        if(notNullOrEmpty.test(String.valueOf(editOrderDetails.getQuantity()))){
            //Make sure quantity is at least 0 not "" or null
            foundOrderDetails.setQuantity(editOrderDetails.getQuantity());
        }
        if(notNullOrEmpty.test(String.valueOf(editOrderDetails.getComments()))){
            foundOrderDetails.setComments(editOrderDetails.getComments());
        }
        return orderDetailsDao.update(foundOrderDetails);
    }
}

