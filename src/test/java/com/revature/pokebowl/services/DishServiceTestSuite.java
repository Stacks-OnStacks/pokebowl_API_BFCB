package com.revature.pokebowl.services;

import com.revature.pokebowl.dish.*;
import com.revature.pokebowl.member.Member;
import org.junit.jupiter.api.*;
import java.sql.Date;

import static org.mockito.Mockito.*;

public class DishServiceTestSuite {

    DishService sut ;
    DishDao mockDishDao;

    @BeforeEach // this goes ahead and re-creates our sut every single test, so we are working with a fresh instance
    public void testPrep(){
        mockDishDao = mock(DishDao.class); // mocktail of the MemberDao Class not an actual instance, prevents massive memory
        sut = new DishService(mockDishDao);
    }

    @Test
    public void test_isDishValid_returnTrue_givenValidDish(){
        // 3 A's
        // Arrange - setting up the information and resources you need for the test
        // Act - performing the actual action on the unit your testing (invoking a method)
        // Assert - evaluated if the actual result is as expected
    }

    @Test
    public void test_isDishValid_returnFalse_givenInvalidDish(){
        // 3 A's
        // Arrange - setting up the information and resources you need for the test
        // Act - performing the actual action on the unit your testing (invoking a method)
        // Assert - evaluated if the actual result is as expected
    }
}
