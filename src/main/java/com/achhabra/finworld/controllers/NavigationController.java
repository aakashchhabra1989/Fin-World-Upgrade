package com.achhabra.finworld.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.achhabra.finworld.beans.EntityBean;
import com.achhabra.finworld.businessobject.Inflow;
import com.achhabra.finworld.businessobject.Outflow;
import com.achhabra.finworld.model.User;
import com.achhabra.finworld.services.CashflowCalculatorServiceImpl;
import com.achhabra.finworld.services.UserServiceImpl;

@Controller
@RequestMapping("/navigate")
public class NavigationController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private CashflowCalculatorServiceImpl cashflowCalculatorServiceImpl;


	@RequestMapping(method=RequestMethod.GET,value="/premium/loadCashFlow.html" )
	public ModelAndView loadCashFlow(HttpServletRequest request){
		String portfolioId= request.getParameter("portfolioId");
		List<Inflow> inflowList = new ArrayList<Inflow>();
		List<Outflow> outflowList = new ArrayList<Outflow>();

		if(portfolioId !=null){
			System.out.println(portfolioId);
			cashflowCalculatorServiceImpl.setCashFlowForPortfolioId(portfolioId,inflowList,outflowList);
		}		
		ModelAndView modelAndView= new ModelAndView("cashflowCalculator");
		modelAndView.addObject("inflowList",inflowList);
		modelAndView.addObject("outflowList",outflowList);
		modelAndView.addObject("cashFlowList",cashflowCalculatorServiceImpl.getCashFlowTypes());
		modelAndView.addObject("frequencyList",cashflowCalculatorServiceImpl.getFrequencyTypes());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/loadBasicCashFlow.html" )
	public ModelAndView loadBasicCashFlow(HttpServletRequest request){
				
		return new ModelAndView("basicCashflowCalculator");
	}
	@RequestMapping(method=RequestMethod.GET,value= { "/", "/loadHomePage.html" })
	public ModelAndView loadHomePage(HttpServletRequest request){
				
		return new ModelAndView("homePage");
	}

	@RequestMapping(method=RequestMethod.GET,value="/loadServices.html" )
	public ModelAndView loadServices(HttpServletRequest request){
				
		return new ModelAndView("services");
	}

	@RequestMapping(method=RequestMethod.GET,value="/loadPromotionalLinks.html" )
	public ModelAndView loadPromotionalLinks(HttpServletRequest request){
				
		return new ModelAndView("promotionalLinks");
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/loadContactUs.html" )
	public ModelAndView loadContactUs(HttpServletRequest request){
				
		return new ModelAndView("contactUs");
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/loadRegisteration.html" )
	public ModelAndView loadRegisteration(HttpServletRequest request){
				
		return new ModelAndView("registrationPage");
	}

	@RequestMapping(method=RequestMethod.GET,value="/loadMyProfile.html" )
	public ModelAndView loadMyProfile(HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		EntityBean entityBean= getUserServiceImpl().getLoadUser(auth.getName());
		ModelAndView model = new ModelAndView("myProfilePage");
		model.addObject("entityBean",entityBean);
		return model;
	}

	@RequestMapping(method=RequestMethod.GET,value="/loadLoginPage.html" )
	public ModelAndView loadLoginPage(HttpServletRequest request){
		String msg=null;
		if(request.getParameter("logout")!=null && "true".equals(request.getParameter("logout"))){
			request.getSession().removeAttribute("username");
			request.getSession().invalidate();
			msg = "You have logout Successful.";
		}
		return new ModelAndView("loginPage","message",msg);
	}
	@RequestMapping(method=RequestMethod.GET,value="/loadSpringLoginPage.html" )
	public ModelAndView loadSpringLoginPage(HttpServletRequest request){
		return new ModelAndView("login-form");
	}

	@RequestMapping(method=RequestMethod.GET,value="/403.html" )
	public ModelAndView loadAccessDeniedPage(HttpServletRequest request){
		return new ModelAndView("403");
	}
	@RequestMapping(method=RequestMethod.GET,value="/error.html" )
	public ModelAndView loadErrorPage(HttpServletRequest request){
		return new ModelAndView("error");
	}

	@RequestMapping(method=RequestMethod.POST,value="/updateProfile.html" )
	public ModelAndView updateMyProfile(@ModelAttribute("entityBean") EntityBean entityBean,HttpServletRequest request){
		getUserServiceImpl().updateUserDetails(entityBean);
		ModelAndView model = new ModelAndView("myProfilePage");
		model.addObject("entityBean",entityBean);
		return model;
	}


	
	
	
	
	/*	@RequestMapping(method = RequestMethod.GET, value = { "/", "/welcome**" } )
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("homePage");
		return model;

	}
*/
	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}	
	
	public CashflowCalculatorServiceImpl getCashflowCalculatorServiceImpl() {
		return cashflowCalculatorServiceImpl;
	}

	public void setCashflowCalculatorServiceImpl(
			CashflowCalculatorServiceImpl cashflowCalculatorServiceImpl) {
		this.cashflowCalculatorServiceImpl = cashflowCalculatorServiceImpl;
	}
}
