package com.revature.pokebowl.services;

import com.revature.pokebowl.dish.Dish;
import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.memberpayment.Payment;
import com.revature.pokebowl.memberpayment.PaymentService;
import com.revature.pokebowl.order.*;
import com.revature.pokebowl.order.Order;
import com.revature.pokebowl.orderdetails.OrderDetailsService;
import org.junit.jupiter.api.*;
import java.sql.Date;

import static org.mockito.Mockito.*;

public class OrderServiceTestSuite {

    OrderService sut ;
    OrderDao mockOrderDao;
    MemberService mockMemberService;
    OrderDetailsService mockOrderDetailsService;
    PaymentService mockPaymentService;

    @BeforeEach // this goes ahead and re-creates our sut every single test, so we are working with a fresh instance
    public void testPrep(){
        mockOrderDao = mock(OrderDao.class); // mocktail of the MemberDao Class not an actual instance, prevents massive memory
        mockMemberService = mock(MemberService.class);
        mockOrderDetailsService = mock(OrderDetailsService.class);
        mockPaymentService = mock(PaymentService.class);
        sut = new OrderService(mockMemberService,mockPaymentService,mockOrderDetailsService,mockOrderDao);
    }

    @Test
    public void test_isOrderValid_returnTrue_givenValidOrder(){
        // 3 A's

        // Arrange - setting up the information and resources you need for the test
        Payment validPayment = new Payment("id", "name", 1000,new Date(System.currentTimeMillis()), "ccv", "zip", "provider");
        Member validMember = new Member("id", "username", "name", "password", new Date(System.currentTimeMillis()), false);

        Order validOrder = new Order("id", 10, new Date(System.currentTimeMillis()), "address", "zip");
        validOrder.setPayment(validPayment);
        validOrder.setMember(validMember);

        System.out.println(validOrder);
        System.out.println(validOrder.getPayment());
        System.out.println(validOrder.getMember());

        // Act - performing the actual action on the unit your testing (invoking a method)
        boolean actualResult = sut.isOrderValid(validOrder);

        // Assert - evaluated if the actual result is as expected
        Assertions.assertTrue(actualResult);
    }

    @Test
    public void test_isOrderValid_returnFalse_givenInvalidInput(){
        // Arrange some invalid input
        Payment validPayment = new Payment("id", "name", 1000,new Date(System.currentTimeMillis()), "ccv", "zip", "provider");
        Member validMember = new Member("id", "username", "name", "password", new Date(System.currentTimeMillis()), false);

        Order invalidOrder1 = null;
        Order invalidOrder2 = new Order("id", 10, new Date(System.currentTimeMillis()), "address", "zip");
        invalidOrder2.setPayment(validPayment);

        Order invalidOrder3 = new Order("id", 10, new Date(System.currentTimeMillis()), "address", "zip");
        invalidOrder3.setMember(validMember);

        Order invalidOrder4 = new Order(null, 10, new Date(System.currentTimeMillis()), "address", "zip");
        invalidOrder4.setMember(validMember);
        invalidOrder4.setPayment(validPayment);

        Order invalidOrder5 = new Order("id", -1, new Date(System.currentTimeMillis()), "address", "zip");
        invalidOrder5.setMember(validMember);
        invalidOrder5.setPayment(validPayment);

        Order invalidOrder6 = new Order("id", 10, null, "address", "zip");
        invalidOrder6.setMember(validMember);
        invalidOrder6.setPayment(validPayment);

        Order invalidOrder7 = new Order("id", 10, new Date(System.currentTimeMillis()), null, "zip");
        invalidOrder7.setMember(validMember);
        invalidOrder7.setPayment(validPayment);

        Order invalidOrder8 = new Order("id", 10, new Date(System.currentTimeMillis()), "address", null);
        invalidOrder8.setMember(validMember);
        invalidOrder8.setPayment(validPayment);


        //How to test is_admin? It can either be true or false

        boolean actualResult1 = sut.isOrderValid(invalidOrder1);
        boolean actualResult2 = sut.isOrderValid(invalidOrder2);
        boolean actualResult3 = sut.isOrderValid(invalidOrder3);
        boolean actualResult4 = sut.isOrderValid(invalidOrder4);
        boolean actualResult5 = sut.isOrderValid(invalidOrder5);
        boolean actualResult6 = sut.isOrderValid(invalidOrder6);
        boolean actualResult7 = sut.isOrderValid(invalidOrder7);
        boolean actualResult8 = sut.isOrderValid(invalidOrder8);

        // Asserts

        Assertions.assertFalse(actualResult1);
        Assertions.assertFalse(actualResult2);
        Assertions.assertFalse(actualResult3);
        Assertions.assertFalse(actualResult4);
        Assertions.assertFalse(actualResult5);
        Assertions.assertFalse(actualResult6);
        Assertions.assertFalse(actualResult7);
        Assertions.assertFalse(actualResult8);
    }
}
