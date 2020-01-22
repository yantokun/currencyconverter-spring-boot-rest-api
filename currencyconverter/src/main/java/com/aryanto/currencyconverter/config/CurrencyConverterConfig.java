package com.aryanto.currencyconverter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.aryanto.currencyconverter.model.CurrencyUrl;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class CurrencyConverterConfig {
	
	@Value("${fixer.url}")
	private String url;
	
	@Value("${fixer.apikey}")
	private String apikey;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}
	
	@Bean
	public CurrencyUrl currencyUrl() {
		
		CurrencyUrl currencyUrl = new CurrencyUrl();
		currencyUrl.setUrl(url);
		currencyUrl.setApiKey(apikey);
		return currencyUrl;
	}
	

}
