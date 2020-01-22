package com.aryanto.currencyconverter.model;

public class CurrencyUrl {
	
	private String url;
	private String apiKey;
	
	public CurrencyUrl(){
		super();
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getApiKey() {
		return apiKey;
	}

}
