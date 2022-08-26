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
        mockPaymentDao = mock(PaymentDao.class); // mocktail of the PaymentDao Class not an actual instance, prevents massive memory
        sut = new PaymentService(mockMemberService,mockPaymentDao); // this will need a memberService
    }

    @Test
    public void test_isPaymentValid_returnTrue_givenValidPayment(){
        Payment validPayment = new Payment("valid","valid", 0 , new Date(1900,01,01), "valid", "valid", "valid");
        boolean actualResult = sut.isPaymentValid(validPayment);
        Assertions.assertTrue(actualResult);
    }

    @Test
    public void test_isPaymentValid_returnFalse_givenInvalidPayment(){
        Payment invalidPayment1 = null;
        Payment invalidPayment2 = new Payment(null,"valid", 0 , new Date(1900,01,01), "valid", "valid", "valid");
        Payment invalidPayment3 = new Payment("valid",null, 0 , new Date(1900,01,01), "valid", "valid", "valid");
        Payment invalidPayment4 = new Payment("valid","", 0 , new Date(1900,01,01), "valid", "valid", "valid");
       // Payment invalidPayment5 = new Payment("valid","valid", 0 , null, "valid", "valid", "valid");
        Payment invalidPayment6 = new Payment("valid","valid", 0 ,new Date(1900,01,01), null, "valid", "valid");
        Payment invalidPayment7 = new Payment("valid","valid", 0 ,new Date(1900,01,01), "", "valid", "valid");
        Payment invalidPayment8 = new Payment("valid","valid", 0 ,new Date(1900,01,01), "valid",null, "valid");
        Payment invalidPayment9 = new Payment("valid","valid", 0 ,new Date(1900,01,01), "valid","", "valid");
        Payment invalidPayment10 = new Payment("valid","valid", 0 ,new Date(1900,01,01), "valid","VALID", null);
        Payment invalidPayment11 = new Payment("valid","valid", 0 ,new Date(1900,01,01), "valid","VALID", "");

        boolean actualResult1 = sut.isPaymentValid(invalidPayment1);
        boolean actualResult2 = sut.isPaymentValid(invalidPayment2);
        boolean actualResult3 = sut.isPaymentValid(invalidPayment3);
        boolean actualResult4 = sut.isPaymentValid(invalidPayment4);
        //boolean actualResult5 = sut.isPaymentValid(invalidPayment5);
        boolean actualResult6 = sut.isPaymentValid(invalidPayment6);
        boolean actualResult7 = sut.isPaymentValid(invalidPayment7);
        boolean actualResult8 = sut.isPaymentValid(invalidPayment8);
        boolean actualResult9 = sut.isPaymentValid(invalidPayment9);
        boolean actualResult10 = sut.isPaymentValid(invalidPayment10);
        boolean actualResult11 = sut.isPaymentValid(invalidPayment11);

        Assertions.assertFalse(actualResult1);
        Assertions.assertFalse(actualResult2);
        Assertions.assertFalse(actualResult3);
        Assertions.assertFalse(actualResult4);
        //Assertions.assertFalse(actualResult5);
        Assertions.assertFalse(actualResult6);
        Assertions.assertFalse(actualResult7);
        Assertions.assertFalse(actualResult8);
        Assertions.assertFalse(actualResult9);
        Assertions.assertFalse(actualResult10);
        Assertions.assertFalse(actualResult11);
    }
}
