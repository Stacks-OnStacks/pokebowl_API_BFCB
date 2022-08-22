package com.revature.pokebowl.services;

import com.revature.pokebowl.order.*;
import org.junit.jupiter.api.*;
import java.sql.Date;

import static org.mockito.Mockito.*;

public class OrderServiceTestSuite {

    OrderService sut ;
    OrderDao mockOrderDao;

    @BeforeEach // this goes ahead and re-creates our sut every single test, so we are working with a fresh instance
    public void testPrep(){
        mockOrderDao = mock(OrderDao.class); // mocktail of the MemberDao Class not an actual instance, prevents massive memory
        sut = new OrderService(mockOrderDao);
    }

    @Test
    public void test_isOrderValid_returnTrue_givenValidOrder(){
        // 3 A's
        // Arrange - setting up the information and resources you need for the test
        // Act - performing the actual action on the unit your testing (invoking a method)
        // Assert - evaluated if the actual result is as expected
    }

    @Test
    public void test_isOrderValid_returnFalse_givenInvalidOrder(){
        // 3 A's
        // Arrange - setting up the information and resources you need for the test
        // Act - performing the actual action on the unit your testing (invoking a method)
        // Assert - evaluated if the actual result is as expected
    }
}
