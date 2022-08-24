package com.revature.pokebowl.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.dish.dto.responses.DishResponse;
import com.revature.pokebowl.member.dto.requests.EditMemberRequest;
import com.revature.pokebowl.member.dto.requests.NewRegistrationRequest;
import com.revature.pokebowl.member.dto.response.MemberResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import com.revature.pokebowl.util.exceptions.UnauthorizedException;
import com.revature.pokebowl.util.interfaces.Authable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class MemberServlet extends HttpServlet implements Authable {
    private final MemberService memberService;
    private final ObjectMapper objectMapper;
    //private final Logger logger = Logger.getLogger(MemberServlet.class.getName());
    private final Logger logger = LogManager.getLogger();

    public MemberServlet(MemberService memberService, ObjectMapper objectMapper){
        this.memberService = memberService;
        this.objectMapper = objectMapper;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter respWriter = resp.getWriter();
        String memberId = req.getParameter("memberId");

        if (memberId != null) {
            logger.info("memberId entered: {}",memberId);

            try {
                MemberResponse member = memberService.findById(memberId);
                String payload = objectMapper.writeValueAsString(member);
                respWriter.write(payload);
                resp.setStatus(200);
            } catch (InvalidUserInputException e) {
                logger.warn("memberId entered was not reflective of any member in the database. memberId provided was: {}",memberId);
                respWriter.write(e.getMessage());
                resp.setStatus(404);
            }
        } else {
            logger.info("Obtaining all members...");
            List<MemberResponse> members = memberService.readAll();
            String payload = objectMapper.writeValueAsString(members);
            respWriter.write(payload);
            resp.setStatus(200);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // DO NOT DO LOGIN INSIDE OF YOUR MEMBER SERVLET
//        LoginCreds loginCreds = objectMapper.readValue(req.getInputStream(), LoginCreds.class); // this provides the body from the request as a JSON, leveraging Reflections
//
//        Member member = memberService.login(loginCreds.getmember_id(), loginCreds.getPassword());
//
//        resp.getWriter().write("Welcome back to pokebowl " + member.getFullName());
        PrintWriter respWriter = resp.getWriter();
        NewRegistrationRequest member = objectMapper.readValue(req.getInputStream(), NewRegistrationRequest.class);
        try {
            logger.info("User has request to add the following to the database {}", member);
            MemberResponse newMember = memberService.registerMember(member);
            String payload = objectMapper.writeValueAsString(newMember);
            respWriter.write(payload);
            resp.setStatus(201);
        } catch (InvalidUserInputException | ResourcePersistanceException e){
            logger.warn("Exception thrown when trying to persist. Message from exception: {}", e.getMessage());
            respWriter.write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            logger.error("Something unexpected happened and this exception was thrown: {} with message: {}", e.getClass().getName(), e.getMessage());
            respWriter.write(e.getMessage() + " " + e.getClass().getName());
            resp.setStatus(500);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter respWriter = resp.getWriter();
        EditMemberRequest editMember = objectMapper.readValue(req.getInputStream(), EditMemberRequest.class);

        try {
            memberService.update(editMember);

            logger.info("Successfully updated member: {}",editMember);
            String payload = objectMapper.writeValueAsString(editMember);
            respWriter.write(payload);
            resp.setStatus(200);
        } catch (InvalidUserInputException | ResourcePersistanceException e) {
            logger.warn("Exception thrown when trying to persist. Message from exception: {}", e.getMessage());
            respWriter.write(e.getMessage());
            resp.setStatus(404);
        }  catch (Exception e) {
            logger.error("Something unexpected happened and this exception was thrown: {} with message: {}", e.getClass().getName(), e.getMessage());
            respWriter.write(e.getMessage() + " " + e.getClass().getName());
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!checkAdmin(req, resp)) return;

        PrintWriter respWriter = resp.getWriter();
        String memberId = req.getParameter("memberId");

        if(memberId != null){
            logger.info("memberId entered: {}",memberId);

            try {
                memberService.remove(memberId);
                respWriter.write(String.format("Member with id '%s' has been deleted",memberId));
                resp.setStatus(200);
            } catch (InvalidUserInputException e) {
                logger.warn("memberId entered was not reflective of any member in the database. memberId provided was: {}",memberId);
                respWriter.write(e.getMessage());
                resp.setStatus(404);
            }
        } else {
            respWriter.write("This request requires a memberId parameter in the path ?memberId=MEMBER_ID");
            resp.setStatus(400);
        }
    }
}