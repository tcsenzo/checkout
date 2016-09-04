package com.senzo.qettal.checkout.history;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senzo.qettal.checkout.address.AddressDTO;

@JsonSerialize
@JsonInclude(NON_NULL)
public class TheaterDTO {

	@JsonProperty
	private Long id;
	@JsonProperty
	private String name;
	@JsonProperty
	private AddressDTO address;

	/**
	 * @deprecated Jackson eyes only
	 */
	TheaterDTO() {
	}
	
	public TheaterDTO(String name, AddressDTO address) {
		this.name = name;
		this.address = address;
	}


}