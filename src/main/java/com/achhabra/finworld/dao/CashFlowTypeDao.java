/**
 * 
 */
package com.achhabra.finworld.dao;

import java.util.List;

import com.achhabra.finworld.constants.CashFlowTypeEnum;
import com.achhabra.finworld.model.CashFlowType;

/**
 * @author achhabra
 *
 */
public interface CashFlowTypeDao {

	public List<CashFlowType> getAllCashFlowType();
	
	public CashFlowType getCashFlowTypeByCode(String Code);
	
	public CashFlowType getCashFlowTypeById(int id);

	public List<CashFlowType> getAllActiveCashFlowType();
	
	public CashFlowType getActiveCashFlowTypeByCode(String Code);
	
	public CashFlowType getActiveCashFlowTypeById(int id);

	public List<CashFlowType> getActiveCashFlowTypeByType(String cashFlowType);

}
