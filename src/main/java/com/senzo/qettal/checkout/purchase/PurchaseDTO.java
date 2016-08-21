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
	
	@JsonProperty("id")
	private Long id;
	
	/**
	 * @deprecated Jackson eyes only
	 */
	PurchaseDTO() {
	}	
	
	public PurchaseDTO(List<PurchaseItemDTO> items, Long eventId, Long id) {
		this.items = items;
		this.eventId = eventId;
		this.id = id;
	}

	public PurchaseDTO(List<PurchaseItemDTO> items, Long id) {
		this(items, null, id);
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
