package com.aryanto.currencyconverter.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.aryanto.currencyconverter.dao.CurrencyConverterDAO;
import com.aryanto.currencyconverter.model.Country;
import com.aryanto.currencyconverter.model.CurrencyAttribute;
import com.aryanto.currencyconverter.model.CurrencyUrl;
import com.aryanto.currencyconverter.utils.Utils;

@Controller
public class CurrencyConverterController {
	
	@Autowired
	public RestTemplate restTemplate;
	
	@Autowired
	private CurrencyUrl currencyData;
	
	Map<String, String> countryMap;
	Country country;
	
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String currencySetup(Model model, CurrencyConverterDAO currencyDAO, CurrencyAttribute currencyAttribute){
		
		currencyAttribute = new CurrencyAttribute();
		
		//add default value
		currencyAttribute.setBaseCurrency("USD");
		currencyAttribute.setConvertCurrency("MYR");
		currencyAttribute.setFromAmount(1.0);
		currencyAttribute.setHistoricalDate(Utils.getCurrentDate());
		
		countryMap = currencyDAO.getMapCountries();

		model.addAttribute("fromAmount",currencyAttribute.getFromAmount());
		model.addAttribute("countryOptions",countryMap);
		model.addAttribute("countryOptions2",countryMap);
		model.addAttribute("currencyConverterAttribute",currencyAttribute);
		return "index";	
	}
	
	@RequestMapping(value="/currency", method=RequestMethod.POST)
	public String convert(Model model, @ModelAttribute("currencyConverterAttribute") CurrencyAttribute currencyAttribute){
		
		String uri = "";
		String baseCurrency = currencyAttribute.getBaseCurrency();
		String convertCurrency = currencyAttribute.getConvertCurrency();
		double fromAmount = currencyAttribute.getFromAmount();
		String historicalDate = currencyAttribute.getHistoricalDate();
		String currencyRate;
		
		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.scheme("http")
				.host(currencyData.getUrl() + historicalDate)
				.query("access_key={apikey}&symbols={baseCurrency},{convertCurrency}")
				.buildAndExpand(currencyData.getApiKey(),baseCurrency,convertCurrency);
		
		uri = uriComponents.toString();
		
		System.out.println(uri);
		
		ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
		
		System.out.println(resp);
		
		List<String> rateList = Utils.parse(resp.getBody(),baseCurrency,convertCurrency);
		currencyRate = Utils.rateChangeConverter(rateList, fromAmount);
		currencyAttribute.setRateChange(currencyRate);
		model.addAttribute("fromAmount",currencyAttribute.getFromAmount());
		model.addAttribute("countryOptions",countryMap);
		model.addAttribute("countryOptions2",countryMap);
		model.addAttribute("currencyConverterAttribute",currencyAttribute);
		
		return "currencyResult";
	}
}
