package com.senzo.qettal.checkout.purchase;


import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senzo.qettal.checkout.address.Address;
import com.senzo.qettal.checkout.address.AddressDTO;

@JsonSerialize
@JsonInclude(NON_NULL)
public class PurchaseTheaterDTO {

	@JsonProperty
	private Long id;
	@JsonProperty
	private String name;
	@JsonProperty
	private AddressDTO address;

	/**
	 * @deprecated Jackson eyes only
	 */
	PurchaseTheaterDTO() {
	}
	
	public PurchaseTheaterDTO(String name, AddressDTO address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address.toModel();
	}

	public Long getId() {
		return id;
	}

}