package com.achhabra.finworld.businessobject;

public class CashStatement {

	private int year;
		
	private double startBalance;
	
	private double incomeInflation;
	
	private double incomeInflationAmt;
	
	private double yearlyInflowAmt;
	
	private double expenseInflation;
	
	private double expenseInflationAmt;

	private double yearlyOutflowAmt;

	private double endBalance;
	
	private double newInflowOutflowAmt;
	
	private double currentYearAmt;

	private String frequency;
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	public double getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}
	
	public double getIncomeInflation() {
		return incomeInflation;
	}

	public void setIncomeInflation(double incomeInflation) {
		this.incomeInflation = incomeInflation;
	}

	public double getIncomeInflationAmt() {
		return incomeInflationAmt;
	}

	public void setIncomeInflationAmt(double incomeInflationAmt) {
		this.incomeInflationAmt = incomeInflationAmt;
	}

	public double getExpenseInflation() {
		return expenseInflation;
	}

	public void setExpenseInflation(double expenseInflation) {
		this.expenseInflation = expenseInflation;
	}

	public double getExpenseInflationAmt() {
		return expenseInflationAmt;
	}

	public void setExpenseInflationAmt(double expenseInflationAmt) {
		this.expenseInflationAmt = expenseInflationAmt;
	}

	public double getNewInflowOutflowAmt() {
		return newInflowOutflowAmt;
	}

	public void setNewInflowOutflowAmt(double newInflowOutflowAmt) {
		this.newInflowOutflowAmt = newInflowOutflowAmt;
	}

	public double getCurrentYearAmt() {
		return currentYearAmt;
	}

	public void setCurrentYearAmt(double currentYearAmt) {
		this.currentYearAmt = currentYearAmt;
	}

	public double getYearlyInflowAmt() {
		return yearlyInflowAmt;
	}

	public void setYearlyInflowAmt(double yearlyInflowAmt) {
		this.yearlyInflowAmt = yearlyInflowAmt;
	}

	public double getYearlyOutflowAmt() {
		return yearlyOutflowAmt;
	}

	public void setYearlyOutflowAmt(double yearlyOutflowAmt) {
		this.yearlyOutflowAmt = yearlyOutflowAmt;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
		
}
