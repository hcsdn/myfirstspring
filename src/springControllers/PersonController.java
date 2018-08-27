package springControllers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import hibernate.Person;

public class PersonController extends MultiActionController {

	private List<Person> GetAllPersons(){
		List<Person> list = null;
    	try {

    		org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
    	    SessionFactory sessionFactory = configuration.buildSessionFactory();
    	    Session session = sessionFactory.openSession();
            
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Person");
            list = query.list();
           
            transaction.commit();
            
            session.close();
            sessionFactory.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return list;
    	
    }
	
	 private void AddNew(String name){

	    	try {

	    		org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
	    	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    	    Session session = sessionFactory.openSession();
	            
	            //3. 寮�鍚簨鍔�
	            Transaction transaction = session.beginTransaction();
	            
	            //4. 鎵ц淇濆瓨鎿嶄綔
	            Person user = new Person();
	            user.setName(name);
	            session.save(user);
	            
	            //5. 鎻愪氦鏃跺姟
	            transaction.commit();
	            
	            //6. 鍏抽棴 Session
	            session.close();
	            //7. 鍏抽棴 SessionFactory 瀵硅薄
	            sessionFactory.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    }
	
	public ModelAndView addone(HttpServletRequest arg0, HttpServletResponse arg1) {
		AddNew(arg0.getParameter("name"));
		ModelAndView mv = new ModelAndView();
		//mv.addObject("persons",GetAllPersons());
		//mv.setViewName("personlist");
		//return mv;
		
		 Map<String, List<Person>> map = new HashMap<>();
	        map.put("persons", GetAllPersons());
	        return new ModelAndView(new MappingJackson2JsonView(), map);
		
	}
	
	public ModelAndView getall(HttpServletRequest arg0, HttpServletResponse arg1) {
		System.out.println("start method getall");
		ModelAndView mv = new ModelAndView();
		mv.addObject("persons",GetAllPersons());
		mv.setViewName("personlist");
		return mv;
	}

}


