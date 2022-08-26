package com.revature.pokebowl.services;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.dish.DishService;
import com.revature.pokebowl.member.MemberService;
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

        // Act - performing the actual action on the unit your testing (invoking a method)
        boolean actualResult = sut.isDishValid(validDish);

        // Assert - evaluated if the actual result is as expected
        Assertions.assertTrue(actualResult);
    }

    @Test
    public void test_areOrderDetailsValid_returnFalse_givenInvalidInput(){
        // Arrange some invalid input
        Dish invalidDish1 = null;
        Dish invalidDish2 = new Dish(null,"name",1000,"description",true);
        Dish invalidDish3 = new Dish("id",null,1000,"description",true);
        Dish invalidDish4 = new Dish("id","name",-1,"description",true);
        Dish invalidDish5 = new Dish("id","name",1000,null,true);

        //How to test is_admin? It can either be true or false

        boolean actualResult1 = sut.isDishValid(invalidDish1);
        boolean actualResult2 = sut.isDishValid(invalidDish2);
        boolean actualResult3 = sut.isDishValid(invalidDish3);
        boolean actualResult4 = sut.isDishValid(invalidDish4);
        boolean actualResult5 = sut.isDishValid(invalidDish5);

        // Asserts

        Assertions.assertFalse(actualResult1);
        Assertions.assertFalse(actualResult2);
        Assertions.assertFalse(actualResult3);
        Assertions.assertFalse(actualResult4);
        Assertions.assertFalse(actualResult5);
    }
}
