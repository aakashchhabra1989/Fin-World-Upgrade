package com.achhabra.finworld.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class FinActionServlet extends DispatcherServlet{

	 /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("In Fin-World dispatcher servlet init method");
        super.init(config);
    }
    
    @Override
    protected WebApplicationContext initWebApplicationContext()
            throws BeansException {
        System.out.println("In Fin-World dispatcher servlet WebApplicationContext method");
    
        WebApplicationContext wac = super.initWebApplicationContext();
        if(null!=wac){
        System.out.println("WAC Display Name " + wac.getDisplayName());
        String[] beanNames = wac.getBeanDefinitionNames();
        for(String bean:beanNames){
        System.out.println(" bean names " + bean) ;
        }
        }else{
            
            System.out.println("Web Application context is null ") ;
        }
        return wac;
    }
	
}
