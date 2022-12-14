package com.revature.pokebowl.orderdetails;
import com.revature.pokebowl.dish.DishService;
import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.order.Order;
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
    private final OrderDetailsDao orderDetailsDao;
    private final Logger logger = LogManager.getLogger();
    private Order currentOrder;

    // CONSTRUCTOR
    public OrderDetailsService(DishService dishService, OrderDetailsDao orderDetailsDao){
        this.dishService = dishService;
        this.orderDetailsDao = orderDetailsDao;
    }
    public OrderDetailsResponse registerOrderDetails(CreateOrderDetailsRequest newOrderDetailsRequest) throws InvalidUserInputException, ResourcePersistanceException {
        OrderDetails newOrderDetails = new OrderDetails();
        newOrderDetails.setOrder(currentOrder);
        newOrderDetails.setDish(dishService.findByDishName(newOrderDetailsRequest.getDishName()));
        newOrderDetails.setComments(newOrderDetailsRequest.getComments());
        newOrderDetails.setQuantity(newOrderDetailsRequest.getQuantity());
        newOrderDetails.setOrderDetailsId(UUID.randomUUID().toString());
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

    public List<OrderDetailsResponse> readAllCurrentOrder(){
        if (currentOrder == null) return null;
        List<OrderDetailsResponse> orderDetails = orderDetailsDao.findAllByOrderId(currentOrder.getOrderId()).stream().map(OrderDetailsResponse::new).collect(Collectors.toList());
        return orderDetails;
    }

    public List<OrderDetails> readAllSubmitOrder(){
        if (currentOrder == null) return null;
        List<OrderDetails> orderDetails = orderDetailsDao.findAllByOrderId(currentOrder.getOrderId());
        return orderDetails;
    }

    public OrderDetailsResponse findByIdAndOrder(String orderDetailsId,String orderId){
        OrderDetails orderDetails = orderDetailsDao.findByIdAndOrder(orderDetailsId,orderId);
        if (orderDetails == null) return null;
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse(orderDetails);
        return orderDetailsResponse;
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

    public void setCurrentOrder(Order order) {
        currentOrder = order;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public boolean areOrderDetailsValid(OrderDetails orderDetails) {
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        if (orderDetails == null) return false;
        if (!notNullOrEmpty.test(orderDetails.getOrderDetailsId())) return false;
        if (!notNullOrEmpty.test(orderDetails.getComments())) return false;
        if (orderDetails.getQuantity() < 0) return false;
        if (orderDetails.getDish() == null) return false;
        if (orderDetails.getOrder() == null) return false;
        return true;
    }
}

