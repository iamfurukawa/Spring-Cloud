package com.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currencyexchangeservice.controller.model.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	
	ExchangeValue findByFromAndTo(final String from, final String to);
}
