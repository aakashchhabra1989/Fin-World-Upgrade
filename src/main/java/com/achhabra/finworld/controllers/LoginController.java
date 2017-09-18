package com.achhabra.finworld.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.achhabra.finworld.beans.EntityBean;
import com.achhabra.finworld.services.LoginServiceImpl;

@Controller
@RequestMapping("/authenticate")
public class LoginController {
	@Autowired
	public LoginServiceImpl loginServiceImpl;

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView authenticateUser(@ModelAttribute("entityBean") EntityBean entityBean,HttpServletRequest request){
		String message="";
		if(entityBean!=null && entityBean.getUserName()!=null && entityBean.getUserName().trim().length()>0 
				&& entityBean.getUserPassword()!=null && entityBean.getUserPassword().trim().length()>0){
			if(loginServiceImpl.authenticateCredentials(entityBean.getUserName(), entityBean.getUserPassword())){
				message= "user authenticated";
				request.getSession().setAttribute("userName", entityBean.getUserName());
				return new ModelAndView("homePage", "message", message);
			}else{
				message= "authentication failed..";
			}
		}else{
			message="please fill details";
		}
        return new ModelAndView("loginPage", "message", message);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("loginPage");

		return model;

	}
	
	public LoginServiceImpl getLoginServiceImpl() {
		return loginServiceImpl;
	}

	public void setLoginServiceImpl(LoginServiceImpl loginServiceImpl) {
		this.loginServiceImpl = loginServiceImpl;
	}
}
