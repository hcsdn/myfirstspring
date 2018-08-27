package myfirstSpring;
import my.beans.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;

import hibernate.Person;

@WebServlet("/CollectingData")
public class CollectingData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CollectingData() {
        super();
    }
    
    private void TryHibernate(){

    	try {

    		org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
    	    SessionFactory sessionFactory = configuration.buildSessionFactory();
    	    Session session = sessionFactory.openSession();
            
            Transaction transaction = session.beginTransaction();
            
            Person user = new Person();
            user.setName("我的Hibernate");
            session.save(user);
            
            transaction.commit();
            
            session.close();
            sessionFactory.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	TryHibernate();
    	
        ServletContext servletContext = getServletContext();
        response.setContentType("text/html");  
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        
        
        ApplicationContext ctx = (ApplicationContext) servletContext.getAttribute("applicationcontext");
        PersonService data = (PersonService) ctx.getBean("person");        
        out.println("<html><head><title>my first servlet</title></head><body>");
        out.println("running Servlet");
        out.println("Got instance of PersonService");
        out.println(data.getInfo());
        out.println("</body>");
        out.println("</html>");
        
    }
}