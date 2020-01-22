package com.aryanto.currencyconverter.model;


public class ExchangeRate {

	private final long id;
	private String historicalDate;
	private String baseCurrency;
	private String convertCurrency;
	private String exchangeRate;
	
	public ExchangeRate(long id,String historicalDate,String baseCurrency,String convertCurrency,String exchangeRate){
		this.id = id;
		this.historicalDate = historicalDate;
		this.baseCurrency = baseCurrency;
		this.convertCurrency = convertCurrency;
		this.exchangeRate = exchangeRate;
	}
	
	public String getHistoricalDate() {
		return historicalDate;
	}
	public void setHistoricalDate(String historicalDate) {
		this.historicalDate = historicalDate;
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
	
	public long getId() {
		return id;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	
}
