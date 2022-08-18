package com.revature.pokebowl.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.member.dto.requests.EditMemberRequest;
import com.revature.pokebowl.member.dto.requests.NewRegistrationRequest;
import com.revature.pokebowl.member.dto.response.MemberResponse;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.exceptions.ResourcePersistanceException;
import com.revature.pokebowl.util.interfaces.Authable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/member")
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

        String member_id = req.getParameter("member_id"); //CHANGED THIS TO member_id instead of member_id
        Member authMember = (Member) req.getSession().getAttribute("authMember"); // cast the returned object to a member

//        } else
        if(member_id != null) {
            logger.info("member_id entered {}", member_id);
            try {
                MemberResponse member = memberService.findById(member_id);

                String payloadID = objectMapper.writeValueAsString(member);

                resp.getWriter().write(payloadID);
            } catch (InvalidUserInputException e){
                logger.warn("User information entered was not reflective of any member in the databse. member_id provided was: {}", member_id);
                resp.getWriter().write("member_id entered was not found in the database");
                resp.setStatus(404);
            }
        } else {
            List<MemberResponse> members = memberService.readAll();
            String payload = objectMapper.writeValueAsString(members); // mapper parsing from Java Object to JSON
            resp.getWriter().write(payload);
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
        PrintWriter respWriter = resp.getWriter(); // preference play, lot of folks enjoy this
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
        EditMemberRequest editMember = objectMapper.readValue(req.getInputStream(), EditMemberRequest.class);

        try {
            memberService.update(editMember);
            resp.getWriter().write("Member has been successfully updated");
        } catch (InvalidUserInputException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage() + " " + e.getClass().getName());
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(!checkAuth(req, resp)) return;
        String member_id = req.getParameter("member_id");
        if(member_id != null){
            memberService.remove(member_id);
            resp.getWriter().write("Member with " + member_id + " has been deleted");
        } else {
            resp.getWriter().write("This request requires an member_id parameter in the path ?member_id=example@mail.com");
            resp.setStatus(400);
        }
    }


}