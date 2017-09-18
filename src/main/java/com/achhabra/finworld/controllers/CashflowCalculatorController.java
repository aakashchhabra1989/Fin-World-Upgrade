package com.achhabra.finworld.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.achhabra.finworld.beans.BasicCalculatorBean;
import com.achhabra.finworld.businessobject.CashStatement;
import com.achhabra.finworld.businessobject.Inflow;
import com.achhabra.finworld.businessobject.Outflow;
import com.achhabra.finworld.services.CashflowCalculatorServiceImpl;

@Controller
@RequestMapping("/cashflowCalculator")
public class CashflowCalculatorController {
	@Autowired
	private CashflowCalculatorServiceImpl cashflowCalculatorServiceImpl;
	
	@RequestMapping(method=RequestMethod.POST,value="/calculateCashflow.html" )
	public ModelAndView calculateCashFlow(HttpServletRequest request){
		List<Inflow> inflows= new ArrayList<Inflow>();
		List<Outflow> outflows= new ArrayList<Outflow>();
		getCashflowCalculatorServiceImpl().prepareCashflowdata(request,inflows,outflows);
		String cashflowId = (new Date()).toString();
		//getCashflowCalculatorServiceImpl().saveCashFlowData(cashflowId, inflows,outflows);
		cashflowCalculatorServiceImpl.saveCashFlowData(cashflowId, inflows, outflows);
		List<CashStatement> cashStatements= getCashflowCalculatorServiceImpl().calculateCashflow(inflows,outflows);
		ModelAndView model = new ModelAndView("cashflowOutput");
		model.addObject("cashStatements", cashStatements);
		return model;
	}

	@RequestMapping(method=RequestMethod.POST,value="/calculateBasicCashflow.html" )
	public ModelAndView calculateBasicCashFlow(@ModelAttribute("basicCalculatorBean") BasicCalculatorBean basicCalculatorBean,HttpServletRequest request){
		//int age= basicCalculatorBean.getCurrentAge();
		
		List<Inflow> inflows= new ArrayList<Inflow>();
		List<Outflow> outflows= new ArrayList<Outflow>();
		Inflow inflow= new Inflow();
		Calendar calendar= Calendar.getInstance();
		inflow.setFromMonth(1);
		inflow.setFromYear(calendar.get(Calendar.YEAR));
		inflow.setToMonth(12);
		inflow.setToYear(basicCalculatorBean.getIncomeToYear());
		inflow.setAmount(basicCalculatorBean.getIncomeAmount());
		inflow.setInflation(basicCalculatorBean.getIncomeInflation());
		//inflow.setFreq("Yearly");
		inflow.setFreq("1");
		inflows.add(inflow);
		
		Outflow outflow= new Outflow();
		outflow.setFromMonth(1);
		outflow.setFromYear(calendar.get(Calendar.YEAR));
		outflow.setToMonth(12);
		outflow.setToYear(basicCalculatorBean.getExpenseToYear());		
		outflow.setAmount(basicCalculatorBean.getExpenseAmount());
		outflow.setInflation(basicCalculatorBean.getExpenseInflation());
		//outflow.setFreq("Yearly");
		outflow.setFreq("1");
		outflows.add(outflow);
		
		
		List<CashStatement> cashStatements= getCashflowCalculatorServiceImpl().calculateCashflow(inflows,outflows);
		ModelAndView model = new ModelAndView("cashflowCalcResult");
		model.addObject("cashStatements", cashStatements);
		return model;
	}

	public CashflowCalculatorServiceImpl getCashflowCalculatorServiceImpl() {
		return cashflowCalculatorServiceImpl;
	}

	public void setCashflowCalculatorServiceImpl(
			CashflowCalculatorServiceImpl cashflowCalculatorServiceImpl) {
		this.cashflowCalculatorServiceImpl = cashflowCalculatorServiceImpl;
	}

	
}
