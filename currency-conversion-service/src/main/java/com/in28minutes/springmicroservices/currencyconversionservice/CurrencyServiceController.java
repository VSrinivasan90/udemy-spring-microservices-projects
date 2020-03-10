package com.in28minutes.springmicroservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.springmicroservices.currencyconversionservice.entity.CurrencyConversionBean;

@RestController
public class CurrencyServiceController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		HashMap variablesMap=new HashMap();
		variablesMap.put("from", from);
		variablesMap.put("to", to);
		ResponseEntity responseEntity=new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversionBean.class,
				variablesMap);
		CurrencyConversionBean response=(CurrencyConversionBean) responseEntity.getBody();
		System.out.println("response......"+response);
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,
				response.getConversionMultiple().multiply(quantity),
				response.getPort());
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean response=(CurrencyConversionBean) proxy.retrieveExchangeValue(from, to);
		System.out.println("feign response......"+response);
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,
				response.getConversionMultiple().multiply(quantity),
				response.getPort());
	}
	 
}
