package com.revature.pokebowl;
import com.revature.pokebowl.member.Member;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.pokebowl.util.ServletContext;
import com.revature.pokebowl.util.HibernateUtil;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import static com.revature.pokebowl.util.HibernateUtil.getSession;

public class MainDriver {

    public static void main(String[] args) {

        // Instantiate the ServletContext (run the program)
        // ServletContext sc = new ServletContext();
        // sc.run();

        //Test for members
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            //Add new member object
            Member member = new Member();
            member.setMemberId(UUID.randomUUID().toString());
            member.setFullName("test mcTester");
            member.setUserPassword("test mcTester");
            member.setDob(new Date(System.currentTimeMillis()));
            session.save(member);

            transaction.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
        }
    }
}
