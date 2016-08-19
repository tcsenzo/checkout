package com.senzo.qettal.checkout.ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDTO {

	@JsonProperty("qrcode_url")
	private String qrCodeUrl;
	@JsonProperty("event")
	private TicketEventDTO event;
	@JsonProperty
	private TicketOwnerDTO user;
	
	/**
	 * @deprecated Jackson eyes only	
	 */
	TicketDTO() {
	}

	public TicketDTO(String qrCodeUrl, TicketEventDTO event, TicketOwnerDTO user) {
		this.qrCodeUrl = qrCodeUrl;
		this.event = event;
		this.user = user;
	}
	
}
