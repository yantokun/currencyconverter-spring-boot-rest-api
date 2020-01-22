package com.aryanto.currencyconverter.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aryanto.currencyconverter.model.Country;
import com.aryanto.currencyconverter.model.CountryEnum;

@Repository
public class CurrencyConverterDAO {

	private static final List<Country> countries = new ArrayList<Country>();
	
	static{
		initData();
	}
	
	private static void initData(){
		for(CountryEnum eachCountry : CountryEnum.values()){
			countries.add(new Country(eachCountry.getCountryCode(), eachCountry.getCountryName()));
		}
	}
	
	public List<Country>getAllCountries(){
		return countries;
	}
	
	public Map<String, String> getMapCountries(){
		Map<String,String> map = new HashMap<String,String>();
		
		for(Country c: countries){
			map.put(c.getCountryCode(), c.getCountryName());
		}
		
		return map;
	}
}
