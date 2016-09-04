package com.senzo.qettal.checkout.purchase;

import java.math.BigDecimal;

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

	@JsonProperty("unit_price")
	private BigDecimal unitPrice;

	@JsonProperty("total_price")
	private BigDecimal totalPrice;
	
	/**
	 * @deprecated Jackson eyes only	
	 */
	PurchaseItemDTO() {
	}
	
	public PurchaseItemDTO(Long quantity, TicketType type, BigDecimal unitPrice) {
		this.quantity = quantity;
		this.type = type;
		this.unitPrice = unitPrice;
		this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
	}

	public Long getQuantity() {
		return quantity;
	}
	
	public TicketType getType() {
		return type;
	}

}
