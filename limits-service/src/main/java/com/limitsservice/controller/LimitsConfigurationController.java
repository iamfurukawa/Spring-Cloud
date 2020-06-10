package com.limitsservice.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.limitsservice.Configuration;
import com.limitsservice.bean.LimitsConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimitsFromConfiguration() {
		return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	@GetMapping("/falt-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveConfigutarion")
	public LimitsConfiguration retrieveConfiguration() {
		if(new Random().nextInt() % 2 == 0)
			throw new RuntimeException("Unavaliable");
		else
			return new LimitsConfiguration(1234, 1234);
	}
	
	public LimitsConfiguration fallbackRetrieveConfigutarion() {
		return new LimitsConfiguration(132, 132);
	}
}
