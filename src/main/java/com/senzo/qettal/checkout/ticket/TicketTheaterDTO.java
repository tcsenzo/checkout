package com.senzo.qettal.checkout.ticket;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senzo.qettal.checkout.address.AddressDTO;

@JsonSerialize
@JsonInclude(NON_NULL)
public class TicketTheaterDTO {

	@JsonProperty
	private String name;
	@JsonProperty
	private AddressDTO address;

	/**
	 * @deprecated Jackson eyes only
	 */
	TicketTheaterDTO() {
	}

	public TicketTheaterDTO(String name, AddressDTO address) {
		this.name = name;
		this.address = address;
	}
	
}