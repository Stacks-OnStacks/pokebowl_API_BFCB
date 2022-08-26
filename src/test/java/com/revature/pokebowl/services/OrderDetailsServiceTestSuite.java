package com.revature.pokebowl.services;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.dish.DishService;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.order.Order;
import com.revature.pokebowl.order.OrderService;
import com.revature.pokebowl.orderdetails.*;
import org.junit.jupiter.api.*;
import java.sql.Date;

import static org.mockito.Mockito.*;

public class OrderDetailsServiceTestSuite {

    OrderDetailsService sut ;
    OrderDetailsDao mockOrderDetailsDao;
    DishService mockDishService;
    @BeforeEach // this goes ahead and re-creates our sut every single test, so we are working with a fresh instance
    public void testPrep(){
        mockDishService = mock(DishService.class);
        mockOrderDetailsDao = mock(OrderDetailsDao.class); // mocktail of the MemberDao Class not an actual instance, prevents massive memory
        sut = new OrderDetailsService(mockDishService,mockOrderDetailsDao);
    }

    @Test
    public void test_areOrderDetailsValid_returnTrue_givenValidOrderDetails(){
        // 3 A's

        // Arrange - setting up the information and resources you need for the test
        Dish validDish = new Dish("id","name",1000,"description",true);
        Order validOrder = new Order("id", 1000, new Date(System.currentTimeMillis()), "address", "zip");
        OrderDetails validOrderDetails = new OrderDetails("id", 1000, "comments");
        validOrderDetails.setOrder(validOrder);
        validOrderDetails.setDish(validDish);

        // Act - performing the actual action on the unit your testing (invoking a method)
        boolean actualResult = sut.areOrderDetailsValid(validOrderDetails);

        // Assert - evaluated if the actual result is as expected
        Assertions.assertTrue(actualResult);
    }

    @Test
    public void test_areOrderDetailsValid_returnFalse_givenInvalidInput(){
        // Arrange some invalid input
        Dish validDish = new Dish("id","name",1000,"description",true);
        Order validOrder = new Order("id", 1000, new Date(System.currentTimeMillis()), "address", "zip");

        OrderDetails invalidOrderDetails1 = null;
        OrderDetails invalidOrderDetails2 = new OrderDetails("id", 1000, "comments");
        invalidOrderDetails2.setOrder(validOrder);
        OrderDetails invalidOrderDetails3 = new OrderDetails("id", 1000, "comments");
        invalidOrderDetails3.setDish(validDish);
        OrderDetails invalidOrderDetails4 = new OrderDetails(null, 1000, "comments");
        invalidOrderDetails4.setOrder(validOrder);
        invalidOrderDetails4.setDish(validDish);
        OrderDetails invalidOrderDetails5 = new OrderDetails("id", -1, "comments");
        invalidOrderDetails5.setOrder(validOrder);
        invalidOrderDetails5.setDish(validDish);
        OrderDetails invalidOrderDetails6 = new OrderDetails("id", 1000, null);
        invalidOrderDetails6.setOrder(validOrder);
        invalidOrderDetails6.setDish(validDish);


        //How to test is_admin? It can either be true or false

        boolean actualResult1 = sut.areOrderDetailsValid(invalidOrderDetails1);
        boolean actualResult2 = sut.areOrderDetailsValid(invalidOrderDetails2);
        boolean actualResult3 = sut.areOrderDetailsValid(invalidOrderDetails3);
        boolean actualResult4 = sut.areOrderDetailsValid(invalidOrderDetails4);
        boolean actualResult5 = sut.areOrderDetailsValid(invalidOrderDetails5);
        boolean actualResult6 = sut.areOrderDetailsValid(invalidOrderDetails6);

        // Asserts

        Assertions.assertFalse(actualResult1);
        Assertions.assertFalse(actualResult2);
        Assertions.assertFalse(actualResult3);
        Assertions.assertFalse(actualResult4);
        Assertions.assertFalse(actualResult5);
        Assertions.assertFalse(actualResult6);
    }
}
