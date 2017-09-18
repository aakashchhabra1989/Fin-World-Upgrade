package com.achhabra.finworld.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.achhabra.finworld.constants.CashFlowTypeEnum;
@Entity
public class CashFlowType {

	public CashFlowType() {
		super();
	}
	
	
	public CashFlowType(String display, String cashFlowType,
			String code) {
		super();
		this.display = display;
		this.cashFlowType = cashFlowType;
		this.code = code;
	}

	@Id
	@GeneratedValue
	private int id;
	
	private String display;
	
	private String cashFlowType;
	
	private String code;
	
	private Boolean isActive;
	
	private int sortOrder;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}


	/**
	 * @param display the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}


	/**
	 * @return the cashFlowType
	 */
	public String getCashFlowType() {
		return cashFlowType;
	}

	/**
	 * @param cashFlowType the cashflowType to set
	 */
	public void setCashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}


	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public int getSortOrder() {
		return sortOrder;
	}


	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}


	
}
