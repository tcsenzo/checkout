package com.senzo.qettal.checkout.ticket;

import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketEventDTO {

	@JsonProperty(required = true)
	private String name;
	@JsonProperty(required = true)
	private BigDecimal price;
	@JsonProperty(value = "scheduled_date", required = true)
	private Instant scheduledDate;
	@JsonProperty(required = true)
	private TicketTheaterDTO theater;
	
	/**
	 * @deprecated Jackson eyes only
	 */
	TicketEventDTO() {
	}

	public TicketEventDTO(String name, BigDecimal price, Instant scheduledDate, TicketTheaterDTO theater) {
		this.name = name;
		this.price = price;
		this.scheduledDate = scheduledDate;
		this.theater = theater;
	}
	
}
