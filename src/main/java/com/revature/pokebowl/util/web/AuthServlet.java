package com.revature.pokebowl.util.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.util.web.dto.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    public AuthServlet(MemberService memberService, ObjectMapper objectMapper) {
        this.memberService = memberService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginCreds loginCreds = objectMapper.readValue(req.getInputStream(), LoginCreds.class);

        Member member = memberService.login(loginCreds.getUsername(), loginCreds.getPassword());

        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("authMember",member);

        resp.getWriter().write(String.format("<h1>Welcome back to Pokebowl: Rapidash, %s!</h1>",member.getFullName()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.getWriter().write("<h1>You have successfully logged out, <i>Seadra</i> next time!</h1>");
    }
}
