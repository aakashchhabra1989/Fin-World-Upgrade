package com.achhabra.finworld.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.achhabra.finworld.constants.CashFlowTypeEnum;
import com.achhabra.finworld.constants.FrequencyTypeEnum;
import com.achhabra.finworld.util.StaticConstants;

@Entity
@Table(name=StaticConstants.DB_PREFIX+"wealth_profile_cashflow")
public class WealthProfileCashFlow {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="profile_id")
	private WealthProfile wealthProfile;
	
	@ManyToOne
	@JoinColumn(name="cashflowType_id", insertable=false, nullable=true)
	private CashFlowType cashflowType;
	
	private double amount;
	
	private FrequencyTypeEnum freq;
	
	@ManyToOne
	@JoinColumn(name="frequency_id", nullable=true )
	private Frequency frequency;

	private CashFlowTypeEnum cashFlowTypeEnum;
	
	private int fromMonth;
	
	private int fromYear;
	
	private int toMonth;
	
	private int toYear;
	
	private double inflation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CashFlowType getCashflowType() {
		return cashflowType;
	}

	public void setCashflowType(CashFlowType cashflowType) {
		this.cashflowType = cashflowType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public FrequencyTypeEnum getFreq() {
		return freq;
	}

	public void setFreq(FrequencyTypeEnum freq) {
		this.freq = freq;
	}

	public int getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(int fromMonth) {
		this.fromMonth = fromMonth;
	}

	public int getFromYear() {
		return fromYear;
	}

	public void setFromYear(int fromYear) {
		this.fromYear = fromYear;
	}

	public int getToMonth() {
		return toMonth;
	}

	public void setToMonth(int toMonth) {
		this.toMonth = toMonth;
	}

	public int getToYear() {
		return toYear;
	}

	public void setToYear(int toYear) {
		this.toYear = toYear;
	}

	public double getInflation() {
		return inflation;
	}

	public void setInflation(double inflation) {
		this.inflation = inflation;
	}

	/**
	 * @return the wealthProfile
	 */
	public WealthProfile getWealthProfile() {
		return wealthProfile;
	}

	/**
	 * @param wealthProfile the wealthProfile to set
	 */
	public void setWealthProfile(WealthProfile wealthProfile) {
		this.wealthProfile = wealthProfile;
	}

	/**
	 * @return the cashFlowTypeEnum
	 */
	public CashFlowTypeEnum getCashFlowTypeEnum() {
		return cashFlowTypeEnum;
	}

	/**
	 * @param cashFlowTypeEnum the cashFlowTypeEnum to set
	 */
	public void setCashFlowTypeEnum(CashFlowTypeEnum cashFlowTypeEnum) {
		this.cashFlowTypeEnum = cashFlowTypeEnum;
	}

	/**
	 * @return the frequency
	 */
	public Frequency getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}	
	

}
