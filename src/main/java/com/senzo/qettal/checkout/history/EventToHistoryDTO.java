package com.senzo.qettal.checkout.history;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventToHistoryDTO {
	
	@JsonProperty
	private String name;
	
	@JsonProperty(value = "scheduled_date", required = true)
	private Instant scheduledDate;
	
	@JsonProperty
	private TheaterDTO theater;

	/**
	 * @deprecated Jackson eyes only
	 */
	EventToHistoryDTO() {
	}

	public EventToHistoryDTO(String name, Instant scheduledDate, TheaterDTO theater) {
		this.name = name;
		this.scheduledDate = scheduledDate;
		this.theater = theater;
	}

	public String getName() {
		return name;
	}

	public Instant getScheduledDate() {
		return scheduledDate;
	}

	public String getTheaterName() {
		return theater.getName();
	}
	
}
