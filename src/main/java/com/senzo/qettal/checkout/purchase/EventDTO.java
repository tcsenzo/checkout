package com.senzo.qettal.checkout.purchase;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDTO {
	@JsonProperty
	private String name;
	@JsonProperty
	private String description;
	@JsonProperty
	private BigDecimal price;
	
	/**
	 * @deprecated Jackson eyes only
	 */
	EventDTO() {
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
}
