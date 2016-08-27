package com.senzo.qettal.checkout.history;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senzo.qettal.checkout.payment.PaymentStatus;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class PurchaseToHistoryDTO {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("tickets")
	private List<TicketsToHistoryDTO> items;

	@JsonProperty("event")
	private EventToHistoryDTO event;
	
	@JsonProperty("payment_status")
	private PaymentStatus paymentStatus;
	
	
	/**
	 * @deprecated Jackson eyes only
	 */
	public PurchaseToHistoryDTO() {
	}

	public PurchaseToHistoryDTO(Long id, List<TicketsToHistoryDTO> items, EventToHistoryDTO event) {
		this.id = id;
		this.items = items;
		this.event = event;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
		
	}
	
	
}
