package com.senzo.qettal.checkout.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senzo.qettal.checkout.ticket.TicketType;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseItemDTO {

	@JsonProperty
	private Long quantity;
	
	@JsonProperty("ticket_type")
	private TicketType type;
	
	/**
	 * @deprecated Jackson eyes only	
	 */
	PurchaseItemDTO() {
	}
	
	public PurchaseItemDTO(Long quantity, TicketType type) {
		this.quantity = quantity;
		this.type = type;
	}

	public Long getQuantity() {
		return quantity;
	}
	
	public TicketType getType() {
		return type;
	}

}
