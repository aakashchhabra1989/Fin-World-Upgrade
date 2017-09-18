package com.achhabra.finworld.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.achhabra.finworld.beans.EntityBean;
import com.achhabra.finworld.services.UserServiceImpl;
import com.achhabra.finworld.util.StaticConstants;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(method=RequestMethod.POST,value="/register.html" )
	public ModelAndView registerUser(@ModelAttribute("entityBean") EntityBean entityBean,HttpServletRequest request){
		long response= getUserServiceImpl().registerUser(entityBean);
		String message= "";
		if(response == StaticConstants.RESPONSE_EXCEPTION_OCCURED){
			message = "System Error Occured! Please contact your System Administrator.";
		}else if(response == StaticConstants.RESPONSE_ALREADY_EXIST){
			message = "This Email id is already registered with us. Please Click Forget Password, if you do not remember your Password";
		}else{
			message = "Thank You, for joining us.";
		}
		
		return new ModelAndView("homePage", "message", message);
	}

	
	
	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(
			UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}	

}
