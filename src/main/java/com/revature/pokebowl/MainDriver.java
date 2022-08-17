package com.revature.pokebowl;
import com.revature.pokebowl.member.Member;
import org.hibernate.Session;

import com.revature.pokebowl.util.ServletContext;
import com.revature.pokebowl.util.HibernateUtil;
import java.sql.Date;

public class MainDriver {

    public static void main(String[] args) {

        // Instantiate the ServletContext (run the program)
        ServletContext sc = new ServletContext();
        sc.run();

        //Test for members
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Add new memberloyee object
        Member member = new Member();
        member.setFull_name("test mcTester");
        member.setUser_password("test mcTester");
        member.setDob(new Date(System.currentTimeMillis()));
        session.save(member);

        session.getTransaction().commit();
        HibernateUtil.closeSession();
    }
}
