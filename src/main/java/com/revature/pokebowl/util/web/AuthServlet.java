package com.revature.pokebowl.util.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.util.exceptions.InvalidUserInputException;
import com.revature.pokebowl.util.web.dto.LoginCreds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LogManager.getLogger();

    public AuthServlet(MemberService memberService, ObjectMapper objectMapper) {
        this.memberService = memberService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LoginCreds loginCreds = objectMapper.readValue(req.getInputStream(), LoginCreds.class);

            Member member = memberService.login(loginCreds.getUsername(), loginCreds.getPassword());
            if (member == null) throw new InvalidUserInputException("login credentials did not match any member in the database");

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authMember", member);
            resp.addCookie(new Cookie("member_id", member.getMemberId()));// this sends a cookie to the user with their id
            resp.addCookie(new Cookie("full_name", member.getFullName().replaceAll("\\s+","")));// also sending back their full_name which cannot have any spaces in it.
            resp.getWriter().write(String.format("<h1>Welcome back to Pokebowl: Rapidash, %s!</h1>", member.getFullName()));
        } catch (InvalidUserInputException e) {
            logger.warn("User information entered was not reflective of any member in the database");
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        memberService.logout();
        req.getSession().invalidate();
        resp.getWriter().write("<h1>You have successfully logged out, <i>Seadra</i> next time!</h1>");
    }
}
