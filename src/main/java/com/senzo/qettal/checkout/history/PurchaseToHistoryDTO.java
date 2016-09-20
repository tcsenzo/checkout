package com.senzo.qettal.checkout.history;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
	
	@JsonProperty(value = "date", required = true)
	private LocalDateTime date;
	
	@JsonProperty("total_price")
	private BigDecimal totalPrice;
	
	
	/**
	 * @deprecated Jackson eyes only
	 */
	public PurchaseToHistoryDTO() {
	}

	public PurchaseToHistoryDTO(Long id, LocalDateTime date, List<TicketsToHistoryDTO> items, EventToHistoryDTO event) {
		this.id = id;
		this.date = date;
		this.items = items;
		this.event = event;
		this.totalPrice = this.items.stream().map(TicketsToHistoryDTO::getPaidPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
		
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEventName(){
		return event.getName();
	}
	
	public String getTheaterName(){
		return event.getTheaterName();
	}
	
	public LocalDateTime getScheduledDate(){
		return event.getScheduledDate();
	}
	
}
