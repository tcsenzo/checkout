package com.senzo.qettal.checkout.purchase;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseItemDTO {

	@JsonProperty("event_id")
	private Long eventId;
	@JsonProperty
	private Long quantity;
	
	/**
	 * @deprecated Jackson eyes only	
	 */
	PurchaseItemDTO() {
	}

	public Long getEventId() {
		return eventId;
	}

	public Long getQuantity() {
		return quantity;
	}

}
