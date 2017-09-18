package com.achhabra.finworld.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.achhabra.finworld.beans.EntityBean;
import com.achhabra.finworld.services.UserServiceImpl;

public class FinInterceptor implements HandlerInterceptor{

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("before");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		if(auth !=null && auth.getName()!=null && !"".equals(auth.getName().trim())){
			//EntityBean entityBean= getUserServiceImpl().getLoadUser(auth.getName());
			List<KeyValuePair> portfolios= getUserServiceImpl().getUserPortfolio(auth.getName());
			if(modelAndView==null){
				modelAndView= new ModelAndView();			
			}
			modelAndView.addObject("userPortfolios",portfolios);
		}
		System.out.println("post");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("completion");
		
	}
	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}	
	

}
