package com.revature.pokebowl.member;

import com.revature.pokebowl.member.dto.requests.EditMemberRequest;
import com.revature.pokebowl.member.dto.requests.NewRegistrationRequest;
import com.revature.pokebowl.member.dto.response.MemberResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemberService {
    // Attributes

    private final MemberDao memberDao;
    private Member sessionMember = null;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    // Methods
    public MemberResponse registerMember(NewRegistrationRequest newRegistration) throws InvalidUserInputException, ResourcePersistanceException{

        Member newMember = new Member();

        newMember.setUsername(newRegistration.getUsername());
        newMember.setFullName(newRegistration.getFullName());
        newMember.setDob(newRegistration.getDob());
        newMember.setUserPassword(newRegistration.getPassword());
        newMember.setMemberId(UUID.randomUUID().toString());
        newMember.setDob(newRegistration.getDob());

        logger.info("Member registration service has begun with the provided info: {}", newMember);
        if (!isMemberValid(newMember)) {
            throw new InvalidUserInputException("User input was invalid");
        }

        if(!isUsernameAvailable(newMember.getUsername())){
            throw new ResourcePersistanceException("Username is already registered please try logging in.");
        }

        memberDao.create(newMember);
        return new MemberResponse(newMember);

    }

    // TODO: NEW READ ME (Lines 43-73)
    public Member login(String email, String password){
        Member member = memberDao.loginCredentialCheck(email, password);
        sessionMember = member;
        return member;
    }

    // TODO: NEW READ ME (Lines 76 - 105)
    public List<MemberResponse> readAll(){

        // Streams are a form of functional programming this is form a declarative programming
        List<MemberResponse> members = memberDao.findAll()
                .stream()//this reads through each value inside the collection
                //.map(member -> new MemberResponse(member))
                // this is leveraging (::) which is know as the method reference operator, it's taking the method from MemberReponse and applying to all objects in the stream
                .map(MemberResponse::new)
                .collect(Collectors.toList());
        return members;
    }
    public MemberResponse findByUsername(String username){

        Member member = memberDao.findByUsername(username);
        if (member == null) return null;

        MemberResponse responseMember = new MemberResponse(member);
        return responseMember;
    }

    public boolean isMemberValid(Member newMember){
        if(newMember == null) return false;
        if(newMember.getMemberId() == null || newMember.getMemberId().trim().equals("")) return false;
        if(newMember.getUsername() == null || newMember.getUsername().trim().equals("")) return false;
        if(newMember.getFullName() == null || newMember.getFullName().trim().equals("")) return false;
        if(newMember.getDob() == null || newMember.getDob().toString().trim().equals("")) return false;
        if(newMember.getUserPassword() == null || newMember.getUserPassword().trim().equals("")) return false;
        return true;
    }

    public boolean isUsernameAvailable(String username){
        return memberDao.checkUsername(username);
    }
    public boolean remove(String username){
        return memberDao.delete(username);
    }
    public boolean update(EditMemberRequest editMember) throws IOException {
        Member foundMember = memberDao.findById(editMember.getId());
        // Predicate - to evaluate a true or false given a lambda expression
        // Lambda expression (arrow notation) - a syntax for a SINGULAR function
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        // Example of Automatic Dirty Checking
        if(notNullOrEmpty.test(editMember.getFullName())){
            foundMember.setFullName(editMember.getFullName());
        }
        if(notNullOrEmpty.test(editMember.getPassword())){
            foundMember.setUserPassword(editMember.getPassword());
        }
        if(notNullOrEmpty.test(editMember.getUsername())){
            if(!isUsernameAvailable(editMember.getUsername())){
                throw new ResourcePersistanceException("The provided username is already registered");
            }
            foundMember.setUsername(editMember.getUsername());
        }
        return memberDao.update(foundMember);
    }

    public Member getSessionMember(){
        return sessionMember;
    }

    public void logout() {
        sessionMember = null;
    }

}