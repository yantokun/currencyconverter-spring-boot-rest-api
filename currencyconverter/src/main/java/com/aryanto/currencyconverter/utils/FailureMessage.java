package com.aryanto.currencyconverter.utils;

public class FailureMessage {
	
	public static final String BAD_REQUEST = "Bad Request. Please provide valid format for specific input. ie."
											+ " date=yyyy-mm-dd,"
											+ " baseCurrency=MYR"
											+ " convertCurrency=USD ."
											+ " amount=1 or amount=2.33"
											+ " Please refer to fixer.io/documentation for more details on Country code";
	
	public static final String INTERNAL_ERROR = "Internal server error. Check your request missing parameter or"
												+ "contact the administrator";
	public static final String NOT_FOUND = "Page not found.";
	public static final String NOT_AVAILABLE ="Error message not available";

}
