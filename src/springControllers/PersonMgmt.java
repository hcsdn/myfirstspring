package springControllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hibernate.Person;

@Controller
public class PersonMgmt {
	@RequestMapping(value = "/personmgmt/add")
	public @ResponseBody List<Person> Add(@RequestParam String name) {
		System.out.println("add one-----------------");
		return AddNew(name);
	}
	
	@RequestMapping(value = "/personmgmt/teststr")
	public @ResponseBody String TestStr() {
		return new String("wo kao, yueguangbaohe");
	}
	
	@RequestMapping(value = "/personmgmt/newtolist")
	public ModelAndView Newtolist(@RequestParam String name) {
		System.out.println("invoking newtolist");
		List<Person> lt =  AddNew(name);
		ModelAndView mv = new ModelAndView();
		mv.addObject("persons",lt);
		mv.setViewName("personlist");
		return mv;
	}
	
	
	
	@RequestMapping(value = "/personmgmt/find")
	public @ResponseBody String Find() {
		System.out.println("add one-----------------");
		return "find";
		
	}
	
	 private List<Person> AddNew(String name){
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
	            
	            
	            Query query = session.createQuery("from Person");
	            //2銆佷娇鐢≦uery瀵硅薄鐨刲ist鏂规硶寰楀埌鏁版嵁闆嗗悎
	            List<Person> list = query.list();
	           
	            //6. 鍏抽棴 Session
	            session.close();
	            //7. 鍏抽棴 SessionFactory 瀵硅薄
	            sessionFactory.close();
	            return list;
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	 
	    }
	
	 @RequestMapping(value = "/personmgmt/index")
	 public ModelAndView Index() {
		 System.out.println("personmgmt/index");
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("index");
		 return mv;
	 }
}
