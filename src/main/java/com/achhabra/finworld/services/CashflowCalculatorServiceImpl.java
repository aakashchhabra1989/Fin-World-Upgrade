package com.achhabra.finworld.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.achhabra.finworld.businessobject.CashStatement;
import com.achhabra.finworld.businessobject.Inflow;
import com.achhabra.finworld.businessobject.Outflow;
import com.achhabra.finworld.constants.CashFlowTypeEnum;
import com.achhabra.finworld.dao.CashFlowTypeDaoImpl;
import com.achhabra.finworld.dao.FrequencyDaoImpl;
import com.achhabra.finworld.model.CashFlowType;
import com.achhabra.finworld.model.Frequency;
import com.achhabra.finworld.model.User;
import com.achhabra.finworld.model.WealthProfile;
import com.achhabra.finworld.model.WealthProfileCashFlow;
import com.achhabra.finworld.util.CommonUtils;
import com.achhabra.finworld.util.ConnectionUtil;
import com.achhabra.finworld.util.StaticConstants;
@Service
@Transactional
public class CashflowCalculatorServiceImpl implements CashflowCalculatorService{
	
	private double currentLiquidity = 0;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private CashFlowTypeDaoImpl cashFlowTypeDaoImpl;
	
	@Autowired
	private FrequencyDaoImpl frequencyDaoImpl;

	public List<CashFlowType> getCashFlowTypes(){
		return cashFlowTypeDaoImpl.getAllCashFlowType();
	}

	public List<Frequency> getFrequencyTypes(){
		return frequencyDaoImpl.getAllFequencies();
	}
	
	public void prepareCashflowdata(HttpServletRequest request,List<Inflow> inflowList, List<Outflow> outFlowList){		
		setInflowList(request,inflowList);
		setOutflowList(request,outFlowList);
	}
	
	private void setInflowList(HttpServletRequest request,List<Inflow> list){		
		String prefix = "income";
		Integer count = Integer.parseInt((String)request.getParameter("incomeRowCount"));
		for(int i=0,j=0; i<=count ; i++){
			String id= prefix+"_type_"+i;
			if(request.getParameter(id)!=null){
				Inflow inflow = new Inflow();
				inflow.setId(j);
				id= prefix+"_type_"+i;
				inflow.setCashflowType((String)request.getParameter(id));
				id= prefix+"_amount_"+i;
				inflow.setAmount(Double.valueOf(request.getParameter(id)));
				id= prefix+"_freq_"+i;
				inflow.setFreq((String)request.getParameter(id));
				
				id= prefix+"_fromDate_"+i;
				inflow.setFromMonth(CommonUtils.getMonth((String)request.getParameter(id)));				
				inflow.setFromYear(CommonUtils.getYear((String)request.getParameter(id)));
				
				id= prefix+"_toDate_"+i;
				inflow.setToMonth(CommonUtils.getMonth((String)request.getParameter(id)));
				inflow.setToYear(CommonUtils.getYear((String)request.getParameter(id)));
				
				id= prefix+"_inflation_"+i;
				inflow.setInflation(Double.valueOf(request.getParameter(id)));
				
				list.add(inflow);				
				++j;
			}			
		}
	}
	
	private void setOutflowList(HttpServletRequest request, List<Outflow> list){
		Integer count = Integer.parseInt((String)request.getParameter("expenseRowCount"));
		String prefix = "expense";
		for(int i=0,j=0; i<=count ; i++){
			String id= prefix+"_type_"+i;
			if(request.getParameter(id)!=null){
				Outflow outflow= new Outflow();
				outflow.setId(j);
				id= prefix+"_type_"+i;
				outflow.setCashflowType((String)request.getParameter(id));
				id= prefix+"_amount_"+i;
				outflow.setAmount(Double.valueOf(request.getParameter(id)));
				id= prefix+"_freq_"+i;
				outflow.setFreq((String)request.getParameter(id));
				
				id= prefix+"_fromDate_"+i;
				outflow.setFromMonth(CommonUtils.getMonth((String)request.getParameter(id)));				
				outflow.setFromYear(CommonUtils.getYear((String)request.getParameter(id)));
				
				id= prefix+"_toDate_"+i;
				outflow.setToMonth(CommonUtils.getMonth((String)request.getParameter(id)));
				outflow.setToYear(CommonUtils.getYear((String)request.getParameter(id)));
				
				id= prefix+"_inflation_"+i;
				outflow.setInflation(Double.valueOf(request.getParameter(id)));
				
				list.add(outflow);				
				++j;
			}			
		}
	}

	public List<CashStatement> calculateCashflow(List<Inflow> inflows, List<Outflow> outflows) {
		HashMap<Integer,List<CashStatement>> inflowCashStatementMap= calculateInflationOfInflow(inflows);
		HashMap<Integer,List<CashStatement>> outflowCashStatementMap= calculateInflationOfOutflow(outflows);
		
		
		List<Integer> uniList= new ArrayList<Integer>();
		Set<Integer> uniInflowSet = inflowCashStatementMap.keySet();
		Set<Integer> uniOutflowSet = outflowCashStatementMap.keySet();
		Set<Integer> uniSet = new HashSet<Integer>();
		uniSet.addAll(uniInflowSet);
		uniSet.addAll(uniOutflowSet);
		uniList.addAll(uniSet);
		Collections.sort(uniList);
		List<CashStatement> finalCashStatements = new ArrayList<CashStatement>();
		boolean isFirstYear= true;
		double lastEOYBalance = 0;
		for(int year : uniList){
			List<CashStatement> inflowStatements=  inflowCashStatementMap.get(year);
			List<CashStatement> outflowStatements=  outflowCashStatementMap.get(year);
			CashStatement finalCashStatementOfYear= new CashStatement();
			finalCashStatementOfYear.setYear(year);
			if(isFirstYear){
				finalCashStatementOfYear.setStartBalance(currentLiquidity);
				isFirstYear= false;
			}else{
				finalCashStatementOfYear.setStartBalance(lastEOYBalance);
			}
			consolidateResult(inflowStatements,outflowStatements,finalCashStatementOfYear);
			lastEOYBalance= finalCashStatementOfYear.getEndBalance();
			finalCashStatements.add(finalCashStatementOfYear);
		}
		return finalCashStatements;		
	}

	private void consolidateResult(List<CashStatement> inflowStatements, List<CashStatement> outflowStatements, CashStatement finalCashStatementOfYear) {
		int avgInflation = 1;
		if(inflowStatements!=null){
			for(CashStatement statement : inflowStatements){
				//finalCashStatementOfYear.setStartBalance(finalCashStatementOfYear.getStartBalance() + statement.getStartBalance());
				finalCashStatementOfYear.setIncomeInflation((finalCashStatementOfYear.getIncomeInflation() + statement.getIncomeInflation())/avgInflation);
				finalCashStatementOfYear.setIncomeInflationAmt(finalCashStatementOfYear.getIncomeInflationAmt() + statement.getIncomeInflationAmt());
				finalCashStatementOfYear.setYearlyInflowAmt(finalCashStatementOfYear.getYearlyInflowAmt() + statement.getYearlyInflowAmt());
				//finalCashStatementOfYear.setEndBalance(finalCashStatementOfYear.getEndBalance() + statement.getEndBalance());
				++avgInflation;
			}
		}
		avgInflation = 1;
		if(outflowStatements!=null){
			for(CashStatement statement : outflowStatements){
				//finalCashStatementOfYear.setStartBalance(finalCashStatementOfYear.getStartBalance() - statement.getStartBalance());
				finalCashStatementOfYear.setExpenseInflation((finalCashStatementOfYear.getExpenseInflation() + statement.getExpenseInflation())/avgInflation);
				finalCashStatementOfYear.setExpenseInflationAmt(finalCashStatementOfYear.getExpenseInflationAmt() + statement.getExpenseInflationAmt());
				finalCashStatementOfYear.setYearlyOutflowAmt(finalCashStatementOfYear.getYearlyOutflowAmt() + statement.getYearlyOutflowAmt());
				//finalCashStatementOfYear.setEndBalance(finalCashStatementOfYear.getEndBalance() - statement.getEndBalance());
				++avgInflation;
			}
		}
		finalCashStatementOfYear.setEndBalance(finalCashStatementOfYear.getStartBalance() + 
				finalCashStatementOfYear.getYearlyInflowAmt()-finalCashStatementOfYear.getYearlyOutflowAmt());

	}

	private HashMap<Integer,List<CashStatement>> calculateInflationOfInflow(List<Inflow> inflows) {
		
		HashMap<Integer,List<CashStatement>> cashStatementMap= new HashMap<Integer, List<CashStatement>>();
		
		for(Inflow inflow: inflows){
			
			double lastEOYBalance= 0;
			double lastCalculatedAmt= 0;
			double inflation= inflow.getInflation();
			double time= 0;
			double inflationAmt= 0; 
			double newInflowOutflowAmt= 0;
			double currentYearAmt = 0;
			
			for(int i =inflow.getFromYear();i<=inflow.getToYear();i++){
				time= 0;
				CashStatement statement = new CashStatement();
				statement.setYear(i);				
				statement.setIncomeInflation(inflation);
				statement.setFrequency(inflow.getFreq());
				//inflationAmt would be 0 for first year
				if(i==inflow.getFromYear()){					
					newInflowOutflowAmt = inflow.getAmount();
					
					if(inflow.getFromYear() == inflow.getToYear()){						
						if(StaticConstants.FREQUENCY_ONE_TIME.equalsIgnoreCase(inflow.getFreq().trim())){
							currentYearAmt= newInflowOutflowAmt;
						}else{
							time = (inflow.getToMonth()-inflow.getFromMonth()+1)/12;
							currentYearAmt= newInflowOutflowAmt * getRatio(inflow.getFreq()) * time;
						}						
					}else{
						if(StaticConstants.FREQUENCY_ONE_TIME.equalsIgnoreCase(inflow.getFreq().trim())){
							currentYearAmt= newInflowOutflowAmt;
						}else{
							time = (12.0-inflow.getFromMonth()+1)/12;
							currentYearAmt= newInflowOutflowAmt * getRatio(inflow.getFreq()) * time;
						}
					}
					
				}else if(i==inflow.getToYear()){
					//1.0 is for int to double conversion
					time = inflow.getToMonth()*1.0/12;
					inflationAmt = lastCalculatedAmt * inflation / 100;
					newInflowOutflowAmt = lastCalculatedAmt + inflationAmt;															
					currentYearAmt = newInflowOutflowAmt * getRatio(inflow.getFreq()) * time;
				}else{
					time=1.0;
					inflationAmt = (lastCalculatedAmt * inflation / 100);
					newInflowOutflowAmt = lastCalculatedAmt + inflationAmt;
					currentYearAmt = newInflowOutflowAmt * getRatio(inflow.getFreq()) * time;
				}
				
				statement.setNewInflowOutflowAmt(newInflowOutflowAmt);
				statement.setStartBalance(lastEOYBalance);							
				statement.setYearlyInflowAmt(currentYearAmt);
				if(currentYearAmt>0){
					statement.setIncomeInflationAmt(inflationAmt * getRatio(inflow.getFreq()));
				}
				statement.setEndBalance(statement.getStartBalance() + currentYearAmt);
				
				lastCalculatedAmt= newInflowOutflowAmt;
				lastEOYBalance= statement.getEndBalance();
				putInMap(cashStatementMap,i,statement);
			}
		}
		return cashStatementMap;
	}

	private HashMap<Integer,List<CashStatement>> calculateInflationOfOutflow(List<Outflow> outflows) {
		
		HashMap<Integer,List<CashStatement>> cashStatementMap= new HashMap<Integer, List<CashStatement>>();
		
		for(Outflow outflow: outflows){
			
			double lastEOYBalance= 0;
			double lastCalculatedAmt= 0;
			double inflation= outflow.getInflation();
			double time= 0;
			double inflationAmt= 0; 
			double newInflowOutflowAmt= 0;
			double currentYearAmt = 0;
			
			for(int i =outflow.getFromYear();i<=outflow.getToYear();i++){
				time= 0;
				CashStatement statement = new CashStatement();
				statement.setYear(i);				
				statement.setExpenseInflation(inflation);
				statement.setFrequency(outflow.getFreq());
				//inflationAmt would be 0 for first year
				if(i==outflow.getFromYear()){					
					newInflowOutflowAmt = outflow.getAmount();
					
					if(outflow.getFromYear() == outflow.getToYear()){						
						if(StaticConstants.FREQUENCY_ONE_TIME.equalsIgnoreCase(outflow.getFreq().trim())){
							currentYearAmt= newInflowOutflowAmt;
						}else{
							time = (outflow.getToMonth()-outflow.getFromMonth() + 1)/12;
							currentYearAmt= newInflowOutflowAmt * getRatio(outflow.getFreq()) * time;
						}						
					}else{
						if(StaticConstants.FREQUENCY_ONE_TIME.equalsIgnoreCase(outflow.getFreq().trim())){
							currentYearAmt= newInflowOutflowAmt;
						}else{
							time = (12.0-outflow.getFromMonth() + 1)/12;
							currentYearAmt= newInflowOutflowAmt * getRatio(outflow.getFreq()) * time;
						}
					}
					
				}else if(i==outflow.getToYear()){
					//1.0 is for int to double conversion
					time = outflow.getToMonth()*1.0/12;
					inflationAmt = lastCalculatedAmt * inflation / 100;
					newInflowOutflowAmt = lastCalculatedAmt + inflationAmt;															
					currentYearAmt = newInflowOutflowAmt * getRatio(outflow.getFreq()) * time;
				}else{
					time=1.0;
					inflationAmt = (lastCalculatedAmt * inflation / 100);
					newInflowOutflowAmt = lastCalculatedAmt + inflationAmt;
					currentYearAmt = newInflowOutflowAmt * getRatio(outflow.getFreq()) * time;
				}
				
				statement.setNewInflowOutflowAmt(newInflowOutflowAmt);
				statement.setStartBalance(lastEOYBalance);							
				statement.setYearlyOutflowAmt(currentYearAmt);
				if(currentYearAmt>0){
					statement.setExpenseInflationAmt(inflationAmt * getRatio(outflow.getFreq()));
				}
				statement.setEndBalance(statement.getStartBalance() + currentYearAmt);
				
				lastCalculatedAmt= newInflowOutflowAmt;
				lastEOYBalance= statement.getEndBalance();
				putInMap(cashStatementMap,i,statement);
			}
		}
		return cashStatementMap;
	}

	private void putInMap(HashMap<Integer, List<CashStatement>> map, int year, CashStatement statement) {
		List<CashStatement> statementList= map.get(year);
		if(statementList==null){
			statementList= new ArrayList<CashStatement>();
			statementList.add(statement);
			map.put(year, statementList);
		}else{
			statementList.add(statement);
		}
		
	}

	public int getRatio(String freq){
		Frequency frequency= getFrequencyType(freq);
		if(frequency!=null){
			return frequency.getYearlyTransaction();
		}else{
			return 0;
		}
/*		if("Yearly".equalsIgnoreCase(freq.trim())){
			return YEARLY;
		}else if("half_Yearly".equalsIgnoreCase(freq.trim())){
			return HALF_YEARLY;
		}else if("quaterly".equalsIgnoreCase(freq.trim())){
			return QUATERLY;
		}else if("monthly".equalsIgnoreCase(freq.trim())){
			return MONTHLY;
		}else if("half_monthly".equalsIgnoreCase(freq.trim())){
			return HALF_MONTHLY;
		}else if("Weekly".equalsIgnoreCase(freq.trim())){
			return WEEKLY;
		}else if("bi_weekly".equalsIgnoreCase(freq.trim())){
			return BI_WEEKLY;
		}else {
			return 0;
		}*/
	}

	public Frequency getFrequencyTypeById(String freq){
		if(freq!=null){
		Frequency frequency= frequencyDaoImpl.getFrequencyById(Integer.parseInt(freq.trim()));
		return frequency;
		}else{
			return null;
		}
	}
	
	public Frequency getFrequencyType(String freq){
		if(freq!=null){
		Frequency frequency= frequencyDaoImpl.getFrequencyById(Integer.parseInt(freq.trim()));
		return frequency;
		}else{
			return null;
		}
	}
	
	@Override
	public void saveCashFlowData(String profileId, List<Inflow> inflows,
			List<Outflow> outflows) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		User user = getUserServiceImpl().getUser(auth.getName());
		Set<WealthProfile> wealthProfiles= user.getWealthProfiles();
		//when no wealth profile is available initiate it
		if(wealthProfiles==null){
			wealthProfiles = new HashSet<WealthProfile>();
		}
		
		WealthProfile wealthProfile= null;
		// initiate wealth Profile if existing and check if we should replace it.
		for(WealthProfile profile: wealthProfiles){
			if(profileId.equals(profile.getProfileId())){
				wealthProfile= profile;				
				break;
			}
		}
		//if Wealth profile is new
		if(wealthProfile == null){
			wealthProfile = new WealthProfile();
			wealthProfiles.add(wealthProfile);
		}
		
		wealthProfile.setProfileId(profileId);
		wealthProfile.setUser(user);
		wealthProfile.setProfileDescription("");
		wealthProfile.setProfileName("");
		Set<WealthProfileCashFlow> wealthProfileCashFlows= new HashSet<WealthProfileCashFlow>(); 
		wealthProfile.setWealthProfileCashFlows(wealthProfileCashFlows);
		for(Inflow inflow : inflows){
			WealthProfileCashFlow cashFlow = new WealthProfileCashFlow();
			cashFlow.setAmount(inflow.getAmount());
			cashFlow.setCashFlowTypeEnum(CashFlowTypeEnum.INFLOW);
			//cashFlow.setFreq(getFrequencyType(inflow.getFreq()));
			cashFlow.setFrequency(getFrequencyType(inflow.getFreq()));
			cashFlow.setFromMonth(inflow.getFromMonth());
			cashFlow.setFromYear(inflow.getFromYear());
			cashFlow.setToMonth(inflow.getToMonth());
			cashFlow.setToYear(inflow.getToYear());
			cashFlow.setInflation(inflow.getInflation());
			cashFlow.setWealthProfile(wealthProfile);
			wealthProfileCashFlows.add(cashFlow);
		}
		for(Outflow outflow : outflows){
			WealthProfileCashFlow cashFlow = new WealthProfileCashFlow();
			cashFlow.setAmount(outflow.getAmount());
			cashFlow.setCashFlowTypeEnum(CashFlowTypeEnum.OUTFLOW);
			cashFlow.setFrequency(getFrequencyType(outflow.getFreq()));
			cashFlow.setFromMonth(outflow.getFromMonth());
			cashFlow.setFromYear(outflow.getFromYear());
			cashFlow.setToMonth(outflow.getToMonth());
			cashFlow.setToYear(outflow.getToYear());
			cashFlow.setInflation(outflow.getInflation());
			cashFlow.setWealthProfile(wealthProfile);
			wealthProfileCashFlows.add(cashFlow);
		}
		Session session = null;
    	Transaction tx = null;
		try{
			if(ConnectionUtil.getSessionFactory().getCurrentSession()!=null )
				session = ConnectionUtil.getSessionFactory().getCurrentSession();
			else
				session = ConnectionUtil.getSessionFactory().openSession();
    		tx = session.beginTransaction();
    		session.update(user);
			tx.commit();
		}catch(RuntimeException e){
    		try{
    			tx.rollback();
    		}catch(RuntimeException rbe){
    			//log.error("Couldn’t roll back transaction", rbe);
    		}
    		throw e;
    	}finally{
    		if(session!=null){
    			try{
    				session.close();
    			}catch(Exception exception){
    				System.out.println(exception.getMessage());
    			}
    		}
    	}

	}

	public void setCashFlowForPortfolioId(String portfolioId,
			List<Inflow> inflowList, List<Outflow> outflowList) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		User user = getUserServiceImpl().getUser(auth.getName());
		if(user != null && user.getWealthProfiles() != null){
			Set<WealthProfile> wealthProfiles= user.getWealthProfiles();
			for(WealthProfile profile:wealthProfiles){
				if(profile.getId().toString().equals(portfolioId)){
					if(profile.getWealthProfileCashFlows()!=null){
						for(WealthProfileCashFlow profileCashFlow : profile.getWealthProfileCashFlows()){
							if(profileCashFlow.getCashFlowTypeEnum()==CashFlowTypeEnum.INFLOW){
								Inflow inflow= new Inflow();
								inflowList.add(inflow);
								inflow.setAmount(profileCashFlow.getAmount());
								inflow.setInflation(profileCashFlow.getInflation());
								inflow.setFromMonth(profileCashFlow.getFromMonth());
								inflow.setFromYear(profileCashFlow.getFromYear());
								inflow.setToMonth(profileCashFlow.getToMonth());
								inflow.setToYear(profileCashFlow.getToYear());
								//inflow.setCashflowType(profileCashFlow.getCashFlowTypeEnum().)
							}else{
								Outflow outflow= new Outflow();
								outflowList.add(outflow);
								outflow.setAmount(profileCashFlow.getAmount());
								outflow.setInflation(profileCashFlow.getInflation());
								outflow.setFromMonth(profileCashFlow.getFromMonth());
								outflow.setFromYear(profileCashFlow.getFromYear());
								outflow.setToMonth(profileCashFlow.getToMonth());
								outflow.setToYear(profileCashFlow.getToYear());
								
							}
						}
					}
					break;
				}
			}

		}

		
	}	
	
	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}


}
