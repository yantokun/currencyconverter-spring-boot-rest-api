package com.aryanto.currencyconverter.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.aryanto.currencyconverter.model.CurrencyUrl;
import com.aryanto.currencyconverter.model.ExchangeRate;
import com.aryanto.currencyconverter.utils.Utils;

@RestController
@Validated
public class CurrencyConverterJsonController {

	@Autowired
	private CurrencyUrl currencyData;
	
	@Autowired
	public RestTemplate restTemplate;
	
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/exchangeRate")
	public ExchangeRate exchangeRate(@RequestParam(value = "date") String historicalDate,
									@RequestParam(value = "baseCurrency") String baseCurrency,
									@RequestParam(value = "convertCurrency") String convertCurrency,
									@RequestParam(value="amount") double fromAmount) {
		String exchangeRate;
		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.scheme("http")
				.host(currencyData.getUrl() + historicalDate)
				.query("access_key={apikey}&symbols={baseCurrency},{convertCurrency}")
				.buildAndExpand(currencyData.getApiKey(),baseCurrency,convertCurrency);
		
		String uri = uriComponents.toString();
		
		System.out.println(uri);
		
		ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
		
		System.out.println(resp);
		
		List<String> rateList = Utils.parse(resp.getBody(),baseCurrency,convertCurrency);
		exchangeRate = Utils.rateChangeConverter(rateList, fromAmount);
		
		return new ExchangeRate(counter.incrementAndGet(),historicalDate, baseCurrency,convertCurrency,exchangeRate);
	}
	
}
