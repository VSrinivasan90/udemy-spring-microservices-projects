package com.in28minutes.springmicroservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.in28minutes.springmicroservices.currencyexchangeservice.bean.ExchangeValue;

@Component
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue,Long>{

	ExchangeValue findByFromAndTo(String from, String to);
	
}
