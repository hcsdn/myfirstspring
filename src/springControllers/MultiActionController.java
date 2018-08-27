
package springControllers;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MultiActionController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		if (arg0.getParameter("action") != null) {
			String action = arg0.getParameter("action");
			//Method method = this.getClass().getMethod(action);
			Method[] methods = this.getClass().getMethods();
			for(Method m:methods) {
				
				String methodName = m.getName();
				if (methodName.equals(action) ) {
					System.out.println("found method");
					return (ModelAndView)m.invoke(this,arg0, arg1);
				}
			}
			
			/*
			var addpersonmethod = this.getClass().getMethod("addperson");
			Method actionMethod = this.getClass().getMethod(arg0.getParameter("action"));//得到方法对象
			System.out.println("parameters length:" + actionMethod.getParameters().length);
			if (actionMethod != null)
				return (ModelAndView)actionMethod.invoke(this,arg0, arg1);
				*/
		}

	       //1、收集参数、验证参数  
	       //2、绑定参数到命令对象  
	       //3、将命令对象传入业务对象进行业务处理  
	       //4、选择下一个页面  
	       ModelAndView mv = new ModelAndView();  
	       //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
	       mv.setViewName("multiaction");  
	       return mv;  
	}
	
	public ModelAndView addperson(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		  ModelAndView mv = new ModelAndView();  
	       //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
	       mv.setViewName("addperson");  
	       return mv;  
	}
	
}


