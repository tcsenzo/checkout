package com.senzo.qettal.checkout.purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senzo.qettal.checkout.address.Address;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseEventDTO {
	@JsonProperty
	private Long id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String description;
	@JsonProperty
	private BigDecimal price;
	@JsonProperty(value = "scheduled_date", required = true)
	private LocalDateTime scheduledDate;
	@JsonProperty(required = true)
	private PurchaseTheaterDTO theater;
	
	/**
	 * @deprecated Jackson eyes only
	 */
	PurchaseEventDTO() {
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getTheaterName() {
		return theater.getName();
	}

	public Address getTheaterAddress() {
		return theater.getAddress();
	}
	
	public LocalDateTime getScheduledDate() {
		return scheduledDate;
	}
	
}
