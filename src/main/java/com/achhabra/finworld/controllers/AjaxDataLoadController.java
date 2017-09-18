package com.achhabra.finworld.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achhabra.finworld.constants.CashFlowTypeEnum;
import com.achhabra.finworld.dao.CashFlowTypeDaoImpl;
import com.achhabra.finworld.model.CashFlowType;
import com.achhabra.finworld.services.CashflowCalculatorServiceImpl;
import com.achhabra.finworld.util.KeyValuePairJson;
import com.achhabra.finworld.util.StaticConstants;

@Controller
@RequestMapping("/loadAjaxData")
public class AjaxDataLoadController {

	@Autowired
	private CashFlowTypeDaoImpl cashFlowTypeDaoImpl;

	public CashFlowTypeDaoImpl getCashFlowTypeDaoImpl() {
		return cashFlowTypeDaoImpl;
	}

	public void setCashFlowTypeDaoImpl(CashFlowTypeDaoImpl cashFlowTypeDaoImpl) {
		this.cashFlowTypeDaoImpl = cashFlowTypeDaoImpl;
	}

	@RequestMapping(method=RequestMethod.GET,value="/inflowCashflowType.html")
	@Produces(MediaType.APPLICATION_JSON )
	public @ResponseBody List<KeyValuePairJson> getInflowCashFlowType(){
		System.out.println("inside inflow cashflow type");
		List<KeyValuePairJson> keyValuePair = new ArrayList<KeyValuePairJson>();
		List<CashFlowType> cashflowTypes = cashFlowTypeDaoImpl.getActiveCashFlowTypeByType(StaticConstants.CASH_FLOW_TYPE_INFLOW);
		for(CashFlowType cashFlowType: cashflowTypes) {
			keyValuePair.add(new KeyValuePairJson(cashFlowType.getId(), cashFlowType.getDisplay()));
		}
		
		return keyValuePair;
	}

	@RequestMapping(method=RequestMethod.GET,value="/outflowCashflowType.html")
	@Produces(MediaType.APPLICATION_JSON )
	public @ResponseBody List<KeyValuePairJson> getOutflowCashFlowType(){
		System.out.println("inside outflow cashflow type");
		List<KeyValuePairJson> keyValuePair = new ArrayList<KeyValuePairJson>();
		List<CashFlowType> cashflowTypes = cashFlowTypeDaoImpl.getActiveCashFlowTypeByType(StaticConstants.CASH_FLOW_TYPE_OUTFLOW);
		for(CashFlowType cashFlowType: cashflowTypes) {
			keyValuePair.add(new KeyValuePairJson(cashFlowType.getId(), cashFlowType.getDisplay()));
		}
		
		return keyValuePair;
	}

	/*@RequestMapping(method=RequestMethod.GET,value="/cashflowType.html", produces = { MediaType.APPLICATION_JSON })*/
	@RequestMapping(method=RequestMethod.GET,value="/cashflowType.html")
	@Produces(MediaType.APPLICATION_JSON )
	public @ResponseBody List<KeyValuePairJson> cashFlowType(String cashFlowType){
		System.out.println("inside cashflow type");

	/*	ModelAndView model = new ModelAndView("cashflowOutput");
	//	model.addObject("cashStatements", cashStatements);
		return model;*/
/*		String cashFlowTypeReq ="";
		cashFlowTypeReq = (String)request.getParameter("cashFlowType"); 
		if(cashFlowTypeReq==null || cashFlowTypeReq.equals("")) {
			cashFlowTypeReq = (String)request.getAttribute("cashFlowType");
		}
*/		
		List<KeyValuePairJson> cashFlowTypes = new ArrayList<KeyValuePairJson>();
		if("Income".equals(cashFlowType)) {
			cashFlowTypes.add(new KeyValuePairJson("1", "inflow" ));
		}else {
		cashFlowTypes.add(new KeyValuePairJson("1", "outflow" ));
		}
		return cashFlowTypes;
	}
}
