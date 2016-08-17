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
	private String name;
	@JsonProperty
	private AddressDTO address;

	/**
	 * @deprecated Jackson eyes only
	 */
	PurchaseTheaterDTO() {
	}
	
	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address.toModel();
	}

}