package myfirstSpring;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SpringServletContextListener  implements ServletContextListener {


    public SpringServletContextListener () {

    }

    public void contextInitialized(ServletContextEvent arg0) {
    	System.out.println("Running listner");
        ServletContext sct = arg0.getServletContext();
        String config = sct.getInitParameter("configLocation");
        System.out.println(config);
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        sct.setAttribute("applicationcontext", ctx);
        System.out.println("initialized spring context");
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }

}
