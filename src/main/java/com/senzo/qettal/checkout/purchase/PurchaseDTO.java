package com.senzo.qettal.checkout.purchase;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseDTO {

	@JsonProperty
	private List<PurchaseItemDTO> items;

	@JsonProperty("event_id")
	private Long eventId;
	
	public Long getEventId() {
		return eventId;
	}
	
	public List<PurchaseItemDTO> getItems() {
		return items;
	}

	public Long getTotalQuantity() {
		return items.stream().mapToLong(PurchaseItemDTO::getQuantity).sum();
	}

}
