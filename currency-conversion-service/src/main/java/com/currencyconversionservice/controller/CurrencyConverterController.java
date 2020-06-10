package com.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currencyconversionservice.model.CurrencyConversionBean;
import com.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class CurrencyConverterController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable final String from, @PathVariable final String to,
			@PathVariable final BigDecimal quantity) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		log.info("{}", response);
		return CurrencyConversionBean.builder()
				.id(response.getId())
				.from(from)
				.to(to).quantity(quantity)
				.conversionMultiple(response.getConversionMultiple())
				.totalCalculatedAmount(quantity.multiply(response.getConversionMultiple()))
				.port(response.getPort())
				.build();
	}
}
