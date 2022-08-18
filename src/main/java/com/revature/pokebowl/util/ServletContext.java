package com.revature.pokebowl.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.pokebowl.dish.DishDao;
import com.revature.pokebowl.dish.DishService;
import com.revature.pokebowl.dish.DishServlet;
import com.revature.pokebowl.member.MemberDao;
import com.revature.pokebowl.member.MemberService;
import com.revature.pokebowl.member.MemberServlet;

import com.revature.pokebowl.util.web.TestServlet;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class ServletContext {

    // Runs the whole program
    public final void run() {

        // For Elastic Beanstalk
        // new File("src/main/webapp/").mkdirs();
        // new File("target/classes").mkdirs();

        String webappDirLocation = new File("src/main/webapp/").getAbsolutePath();  // absolute path of webapp directory
        String additionalClasses = new File("target/classes").getAbsolutePath();    // absolute path of java classes

        // initialize Tomcat
        Tomcat tomcat = new Tomcat();

        try {
            // initialization of the Servlet Context and giving Tomcat access/knowledge of all pertinent classes in this project
            StandardContext standardContext = (StandardContext) tomcat.addWebapp("/",webappDirLocation);
            WebResourceRoot resourceRoot = new StandardRoot(standardContext);
            resourceRoot.addPreResources(new DirResourceSet(resourceRoot,"/WEB-INF/classes", additionalClasses, "/"));

            standardContext.setResources(resourceRoot); // Give Tomcat access to all classes' information

            // TODO: IMPLEMENT DEPENDENCIES (SERVICES AND DAOS)

            MemberDao memberDao = new MemberDao();
            // PaymentDao paymentDao = new PaymentDao();
            DishDao dishDao = new DishDao();
            // OrdersDao ordersDao = new OrdersDao();
            // OrderDetailsDao orderDetailsDao = new OrderDetailsDao();

            MemberService memberService = new MemberService(memberDao);
            // PaymentService paymentService = new PaymentService(paymentDao);
            DishService dishService = new DishService(dishDao);
            // OrdersService ordersService = new OrdersService(ordersDao);
            // OrderDetailsService orderDetailsService = new OrderDetailsService(orderDetailsDao);

            ObjectMapper objectMapper = new ObjectMapper();   // instantiate the ObjectMapper dependency for JSON marshalling

            tomcat.addServlet("","TestServlet", new TestServlet(objectMapper));
            standardContext.addServletMappingDecoded("/test","TestServlet");

            tomcat.addServlet("","MemberServlet", new MemberServlet(memberService, objectMapper));
            standardContext.addServletMappingDecoded("/member","MemberServlet");

            tomcat.addServlet("","DishServlet", new DishServlet(dishService, objectMapper));
            standardContext.addServletMappingDecoded("/dish","DishServlet");

            // Start the Server and run til termination
            tomcat.start();
            tomcat.getServer().await();

        } catch (ServletException | LifecycleException e){
            throw new RuntimeException(e);
        }

    }
}
