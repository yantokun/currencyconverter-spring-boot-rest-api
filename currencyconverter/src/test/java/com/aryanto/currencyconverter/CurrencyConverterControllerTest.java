package com.aryanto.currencyconverter;

import static org.junit.Assert.assertEquals;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.aryanto.currencyconverter.model.CurrencyUrl;
@SpringBootTest
@RunWith(SpringRunner.class)
@PropertySource("classpath:application.properties")
@WebAppConfiguration
public class CurrencyConverterControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Value("${fixer.url}")
	private String url;
	
	@Value("${fixer.apikey}")
	private String apikey;
	
	@Bean
	public CurrencyUrl currencyUrl() {
		
		CurrencyUrl currencyUrl = new CurrencyUrl();
		currencyUrl.setUrl(url);
		currencyUrl.setApiKey(apikey);
		return currencyUrl;
	}
	
	@Before
	public void setup() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void givenWac_whenServletContext_thenItProvidesCurrencyConverterJsonController() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("currencyConverterJsonController"));
	}
	
	@Test
	public void givenWac_whenServletContext_thenItProvidesCurrencyConverterController() {
	    ServletContext servletContext = wac.getServletContext();
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("currencyConverterController"));
	}
	
	@Test
	public void givenWac_whenServletContext_thenItProvidesBasicErrorController() {
	    ServletContext servletContext = wac.getServletContext();
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("basicErrorController"));
	}
	
	@Test
	public void givenExchangeRateJsonURI_whenMockMVC_thenVerifyResponse() throws Exception{
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/exchangeRate?date=2020-01-21&baseCurrency=MYR&convertCurrency=USD&amount=1"))
	      .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.historicalDate").value("2020-01-21"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.baseCurrency").value("MYR"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.convertCurrency").value("USD"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate").value("4.07"))
	      .andReturn();
	     
	    Assert.assertEquals("application/json", 
	      mvcResult.getResponse().getContentType());
	}
	
	@Test
	public void givenExchangeRateURI_whenMockMVC_thenVerifyResponse() throws Exception{
	    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
	      .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
	      .andReturn();
	     
	    Assert.assertEquals("/view/index.jsp", 
	      mvcResult.getResponse().getForwardedUrl());
	}
	
	@Test
	public void testFixerAPI() {
		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.scheme("http")
				.host(currencyUrl().getUrl() + "2013-12-24")
				.query("access_key={apikey}&symbols={baseCurrency},{convertCurrency}")
				.buildAndExpand(currencyUrl().getApiKey(),"MYR","USD");
	 
	     assertEquals("http://data.fixer.io/api/2013-12-24?access_key="+currencyUrl().getApiKey()+"&symbols=MYR,USD", uriComponents.toUriString());

	}
	
	
	
	

}
