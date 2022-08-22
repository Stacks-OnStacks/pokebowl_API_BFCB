package com.revature.pokebowl.services;

import com.revature.pokebowl.orderdetails.*;
import org.junit.jupiter.api.*;
import java.sql.Date;

import static org.mockito.Mockito.*;

public class OrderDetailsServiceTestSuite {

    OrderDetailsService sut ;
    OrderDetailsDao mockOrderDetailsDao;

    @BeforeEach // this goes ahead and re-creates our sut every single test, so we are working with a fresh instance
    public void testPrep(){
        mockOrderDetailsDao = mock(OrderDetailsDao.class); // mocktail of the MemberDao Class not an actual instance, prevents massive memory
        sut = new OrderDetailsService(mockOrderDetailsDao);
    }

    @Test
    public void test_isOrderDetailsValid_returnTrue_givenValidOrderDetails(){
        // 3 A's
        // Arrange - setting up the information and resources you need for the test
        // Act - performing the actual action on the unit your testing (invoking a method)
        // Assert - evaluated if the actual result is as expected
    }

    @Test
    public void test_isOrderDetailsValid_returnFalse_givenInvalidOrderDetails(){
        // 3 A's
        // Arrange - setting up the information and resources you need for the test
        // Act - performing the actual action on the unit your testing (invoking a method)
        // Assert - evaluated if the actual result is as expected
    }
}
