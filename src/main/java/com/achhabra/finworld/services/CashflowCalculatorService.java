package com.achhabra.finworld.services;

import java.util.List;

import com.achhabra.finworld.businessobject.Inflow;
import com.achhabra.finworld.businessobject.Outflow;

public interface CashflowCalculatorService {
	
	public void saveCashFlowData(String profileId, List<Inflow> inflows,
			List<Outflow> outflows) ;

}
