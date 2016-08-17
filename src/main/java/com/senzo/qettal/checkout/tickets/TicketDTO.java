package com.senzo.qettal.checkout.tickets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDTO {

	@JsonProperty("event")
	private TicketEventDTO event;
	@JsonProperty
	private TicketOwnerDTO user;
	
	/**
	 * @deprecated Jackson eyes only	
	 */
	TicketDTO() {
	}

	public TicketDTO(TicketEventDTO event, TicketOwnerDTO user) {
		this.event = event;
		this.user = user;
	}
	
}
