package com.senzo.qettal.checkout.purchase;

import org.springframework.web.client.HttpClientErrorException;

public class EventNotAvailableException extends RuntimeException {

	public EventNotAvailableException(Exception e) {
		super(e);
	}
	
}
