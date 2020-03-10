package com.in28minutes.springmicroservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springmicroservices.limitsservice.bean.Configuration;
import com.in28minutes.springmicroservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigRestController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimits() {
		//return new LimitConfiguration(1000,1);
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	
}