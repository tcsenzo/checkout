package com.senzo.qettal.checkout.history;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senzo.qettal.checkout.ticket.TicketType;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketsToHistoryDTO {

	@JsonProperty("qrcode_url")
	private String qrCodeUrl;

	@JsonProperty("type")
	private TicketType type;
	
	@JsonProperty("paid_price")
	private BigDecimal paidPrice;

	/**
	 * @deprecated Jackson eyes only
	 */
	TicketsToHistoryDTO() {
	}
	
	public TicketsToHistoryDTO(String qrCodeUrl, TicketType type, BigDecimal paidPrice) {
		this.qrCodeUrl = qrCodeUrl;
		this.type = type;
		this.paidPrice = paidPrice;
	}
	
	
	
}
