package com.senzo.qettal.checkout.purchase;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class PurchaseDTO {

	@JsonProperty
	private List<PurchaseItemDTO> items;

	@JsonProperty("event_id")
	private Long eventId;
	
	/**
	 * @deprecated Jackson eyes only
	 */
	PurchaseDTO() {
	}	
	
	public PurchaseDTO(List<PurchaseItemDTO> items, Long eventId) {
		this.items = items;
		this.eventId = eventId;
	}

	public PurchaseDTO(List<PurchaseItemDTO> items) {
		this(items, null);
	}

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
