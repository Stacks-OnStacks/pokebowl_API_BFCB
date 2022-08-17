package com.revature.pokebowl.util;

import com.revature.pokebowl.dish.Dish;

//import com.revature.pokebowl.member.Member;// no member class yet

import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.memberpayment.MemberPayment;
import com.revature.pokebowl.order.Order;
import com.revature.pokebowl.orderdetails.OrderDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession() throws IOException {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();

            // for Elastic Beanstalk
            // ClassLoader loader = Thread.currentThread().getContextClassLoader();
            // properties.load(loader.getResourceAsStream("hibernate.properties"));
            properties.load(new FileReader("src/main/resources/hibernate.properties"));

            //HERE IS WHERE WE ADD OUR CLASSES
            configuration.addAnnotatedClass(Member.class);
            configuration.addAnnotatedClass(Dish.class);
            // configuration.addAnnotatedClass(Order.class);
            // configuration.addAnnotatedClass(OrderDetails.class);
            // configuration.addAnnotatedClass(MemberPayment.class);


            // ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        if(session == null) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    public static void closeSession() {
        session.close();
        session = null;

    }
}