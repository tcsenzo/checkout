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

	public List<PurchaseItemDTO> getItems() {
		return items;
	}

}
