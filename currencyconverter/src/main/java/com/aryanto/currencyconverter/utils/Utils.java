package com.aryanto.currencyconverter.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static String getCurrentDate(){
		/*For formatting date*/
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String strDate= dtf.format(currentDate);
		
		return strDate;
	}
	
	public static String rateChangeConverter(List<String> list, double fromAmount){
		
		double baseCurrency = Double.parseDouble(list.get(0));
		double convertCurrency = Double.parseDouble(list.get(1));
		double oneUnit;
		String currencyRate;
		
			oneUnit = baseCurrency/convertCurrency;
		
		currencyRate = String.format("%.2f", oneUnit*fromAmount);
		
		
		return currencyRate;
	}
	
	public static List<String> parse(String resp, String baseCurrency, String convertCurrency){
		
		JsonFactory jsonFactory = new JsonFactory();
		List<String> list = new ArrayList<String>();
		ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
		
		try {
			JsonNode rootNode = objectMapper.readTree(resp);
			JsonNode rates = rootNode.path("rates");
			
			Iterator<JsonNode> elements = rates.elements();
			
			while(elements.hasNext()){
				JsonNode currency = elements.next();
				list.add(currency.toString());
			}
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
