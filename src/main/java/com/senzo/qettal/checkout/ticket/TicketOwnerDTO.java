package com.senzo.qettal.checkout.tickets;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class TicketOwnerDTO {

	@JsonProperty
	private String name;

	/**
	 * @deprecated Jackson eyes only
	 */
	TicketOwnerDTO() {
	}

	public TicketOwnerDTO(String name) {
		this.name = name;
	}
	
}