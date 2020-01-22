package com.aryanto.currencyconverter.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Currency implements Serializable{

	private static final long serialVersionUID = -2135667124959066691L;
	
	private double rateBaseCurrency;
	private double rateConvertCurrency;
	
	@Bean
	public Currency currency(){
		return new Currency();
	}

	public double getRateBaseCurrency() {
		return rateBaseCurrency;
	}

	public void setRateBaseCurrency(double rateBaseCurrency) {
		this.rateBaseCurrency = rateBaseCurrency;
	}

	public double getRateConvertCurrency() {
		return rateConvertCurrency;
	}

	public void setRateConvertCurrency(double convertCurrency) {
		this.rateConvertCurrency = convertCurrency;
	}
	
	@JsonProperty("rates")
	public void setRates(Map<String, Object> rates, String rateBaseCurrency, String rateConvertCurrency){
		setRateBaseCurrency((double) rates.get(rateBaseCurrency));
		setRateConvertCurrency((double) rates.get(rateConvertCurrency));
	}

	
}
