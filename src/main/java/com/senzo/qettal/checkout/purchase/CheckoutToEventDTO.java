package com.senzo.qettal.checkout.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckoutToEventDTO {
	@JsonProperty
	private Long quantity;
	
	/**
	 * @deprecated Jackson eyes only
	 */
	CheckoutToEventDTO() {
	}

	public CheckoutToEventDTO(Long quantity) {
		this.quantity = quantity;
	}

}
