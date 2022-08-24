package com.revature.pokebowl.services;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.memberpayment.*;
import org.junit.jupiter.api.*;
import java.sql.Date;
import static org.mockito.Mockito.*;
public class PaymentServiceSuite {

    PaymentService sut ;
    PaymentDao mockPaymentDao;
    MemberService mockMemberService;

    @BeforeEach // this goes ahead and re-creates our sut every single test, so we are working with a fresh instance
    public void testPrep(){
        mockMemberService = mock(MemberService.class);
        mockPaymentDao = mock(PaymentDao.class); // mocktail of the MemberDao Class not an actual instance, prevents massive memory
        sut = new PaymentService(mockMemberService,mockPaymentDao); // this will need a memberService
    }

    @Test
    public void test_isPaymentValid_returnTrue_givenValidPayment(){
        // 3 A's
        // Arrange - setting up the information and resources you need for the test
        // Act - performing the actual action on the unit your testing (invoking a method)
        // Assert - evaluated if the actual result is as expected
    }

    @Test
    public void test_isPaymentValid_returnFalse_givenInvalidPayment(){
        // 3 A's
        // Arrange - setting up the information and resources you need for the test
        // Act - performing the actual action on the unit your testing (invoking a method)
        // Assert - evaluated if the actual result is as expected
    }
}
