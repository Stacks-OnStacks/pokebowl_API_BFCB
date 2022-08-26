package com.revature.pokebowl.services;

import com.revature.pokebowl.member.*;
import org.junit.jupiter.api.*;
import java.sql.Date;

import static org.mockito.Mockito.*;

public class MemberServiceTestSuite {

    MemberService sut ;
    MemberDao mockMemberDao;

    @BeforeEach // this goes ahead and re-creates our sut every single test, so we are working with a fresh instance
    public void testPrep(){
        mockMemberDao = mock(MemberDao.class); // mocktail of the MemberDao Class not an actual instance, prevents massive memory
        sut = new MemberService(mockMemberDao);
    }

    @Test
    public void test_isMemberValid_returnTrue_givenValidMember(){
        // 3 A's

        // Arrange - setting up the information and resources you need for the test
        Member validMember = new Member("valid", "valid", "valid", "valid" , new Date(1900,01,01), false);

        // Act - performing the actual action on the unit your testing (invoking a method)
        boolean actualResult = sut.isMemberValid(validMember);

        // Assert - evaluated if the actual result is as expected
        Assertions.assertTrue(actualResult);
    }

    @Test
    public void test_isMemberValid_returnFalse_givenInvalidInput(){
        // Arrange some invalid input
        Member invalidMember1 = null;
        Member invalidMember2 = new Member(null, "valid", "valid", "valid" , new Date(1900,01,01), false);
        Member invalidMember3 = new Member("valid", null, "valid", "valid" , new Date(1900,01,01), false);
        Member invalidMember4 = new Member("valid", "valid", null, "valid" , new Date(1900,01,01), false);
        Member invalidMember5 = new Member("valid", "valid", "valid", null , new Date(1900,01,01), false);
        Member invalidMember6 = new Member("valid", "valid", "valid", null , null, false);

                            //How to test is_admin? It can either be true or false

        boolean actualResult1 = sut.isMemberValid(invalidMember1);
        boolean actualResult2 = sut.isMemberValid(invalidMember2);
        boolean actualResult3 = sut.isMemberValid(invalidMember3);
        boolean actualResult4 = sut.isMemberValid(invalidMember4);
        boolean actualResult5 = sut.isMemberValid(invalidMember5);
        boolean actualResult6 = sut.isMemberValid(invalidMember6);

        // Asserts

        Assertions.assertFalse(actualResult1);
        Assertions.assertFalse(actualResult2);
        Assertions.assertFalse(actualResult3);
        Assertions.assertFalse(actualResult4);
        Assertions.assertFalse(actualResult5);
        Assertions.assertFalse(actualResult6);
    }
    // This will be uncommented when I can figure out how to register member through Postman Post request
    // This will allow me to add a test user to the database to test login and register
//    @Test
//    public void test_login_returnsMember_givenValidLoginCredentials(){
//        // Arrange
//        String email = "cj@mail.com";
//        String password = "pass";
//
//        //Act
//        Member actualMember = sut.login(email, password);
//
//        // Assert
//        Assertions.assertInstanceOf(Member.class, actualMember);
//    }


//    @Test
//    public void test_registerMember_returnsNewMember_givenValidMember(){
//        Member validMember = new Member("valid", "valid", 12, new Date(2022,8,05), "valid");
//
//        // when mocking we need to do a when/then for any DAO call
//        when(mockMemberDao.findAll()).thenReturn(new LinkedList<Member>());
//        when(mockMemberDao.create(validMember)).thenReturn(validMember);
//
//        Member actualNewMember = sut.registerMember(validMember);
//
//        Assertions.assertInstanceOf(Member.class, actualNewMember);
//        // at the end we need to verify the number of dao calls from this method
//        verify(mockMemberDao, times(1)).create(validMember);
//    }



}