package com.senzo.qettal.checkout.tickets;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	private LocalDateTime scheduledDate;
	@JsonProperty(required = true)
	private TicketTheaterDTO theater;
	
	/**
	 * @deprecated Jackson eyes only
	 */
	TicketEventDTO() {
	}

	public TicketEventDTO(String name, BigDecimal price, LocalDateTime scheduledDate, TicketTheaterDTO theater) {
		this.name = name;
		this.price = price;
		this.scheduledDate = scheduledDate;
		this.theater = theater;
	}
	
}
