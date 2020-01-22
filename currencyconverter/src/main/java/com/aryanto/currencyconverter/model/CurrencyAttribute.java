package com.aryanto.currencyconverter.model;

import java.util.List;

public class CurrencyAttribute {
	
	private String baseCurrency;
	private String convertCurrency;
	private double fromAmount;
	private List<String> rateList;
	private String rateChange;
	private String historicalDate;
	
	public CurrencyAttribute(){
		super();
	}
	
	public CurrencyAttribute(String baseCurrency, String convertCurrency){
		super();
		this.baseCurrency = baseCurrency;
		this.convertCurrency = convertCurrency;
	}
	
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getConvertCurrency() {
		return convertCurrency;
	}
	public void setConvertCurrency(String convertCurrency) {
		this.convertCurrency = convertCurrency;
	}
	public double getFromAmount(){
		return fromAmount;
	}
	public void setFromAmount(double fromAmount){
		this.fromAmount = fromAmount;
	}

	public List<String> getRateList() {
		return rateList;
	}

	public void setRateList(List<String> rateList) {
		this.rateList = rateList;
	}

	public String getRateChange() {
		return rateChange;
	}

	public void setRateChange(String rateChange) {
		this.rateChange = rateChange;
	}

	public String getHistoricalDate() {
		return historicalDate;
	}

	public void setHistoricalDate(String historicalDate) {
		this.historicalDate = historicalDate;
	}
}
