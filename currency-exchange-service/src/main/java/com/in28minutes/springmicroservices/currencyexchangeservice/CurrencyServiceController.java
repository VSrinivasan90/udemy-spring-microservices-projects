package com.in28minutes.springmicroservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springmicroservices.currencyexchangeservice.bean.ExchangeValue;

@RestController
public class CurrencyServiceController {

	@Autowired
	protected Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping(path="/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveCurrencyValue(@PathVariable(name="from")String from, @PathVariable(name="to") String to) {
		//ExchangeValue exchangeValue = new ExchangeValue(100L,from,to,new BigDecimal(65));
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;		
	}
	
}
