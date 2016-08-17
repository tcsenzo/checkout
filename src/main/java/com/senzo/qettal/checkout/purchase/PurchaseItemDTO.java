package com.senzo.qettal.checkout.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseItemDTO {

	@JsonProperty
	private Long quantity;
	
	/**
	 * @deprecated Jackson eyes only	
	 */
	PurchaseItemDTO() {
	}

	public Long getQuantity() {
		return quantity;
	}

}
