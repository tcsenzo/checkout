package com.senzo.qettal.checkout.history;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senzo.qettal.checkout.payment.PaymentStatus;
import com.senzo.qettal.checkout.purchase.PurchaseItemDTO;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class TheaterPurchaseToHistoryDTO {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("items")
	private List<PurchaseItemDTO> items;

	@JsonProperty("event")
	private EventToHistoryDTO event;
	
	@JsonProperty("payment_status")
	private PaymentStatus paymentStatus;
	
	@JsonProperty(value = "date", required = true)
	private Instant date;
	
	@JsonProperty("total_price")
	private BigDecimal totalPrice;
	
	
	/**
	 * @deprecated Jackson eyes only
	 */
	public TheaterPurchaseToHistoryDTO() {
	}

	public TheaterPurchaseToHistoryDTO(Long id, Instant date, List<PurchaseItemDTO> items, EventToHistoryDTO event) {
		this.id = id;
		this.date = date;
		this.items = items;
		this.event = event;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
		
	}
	
	
}
