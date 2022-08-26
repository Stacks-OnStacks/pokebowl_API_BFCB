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
        ServletContext sc = new ServletContext();
        sc.run();
    }
}
