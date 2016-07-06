package com.senzo.qettal.checkout.purchase;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PurchaseItemConverter {

	public PurchaseItem convert(PurchaseItemDTO purchaseItemDTO) {
		RestTemplate restTemplate = new RestTemplate();
		EventDTO event = restTemplate.getForObject("http://localhost:8080/events/"+purchaseItemDTO.getEventId(), EventDTO.class);
		return new PurchaseItem(event.getName(), event.getDescription(), purchaseItemDTO.getQuantity(), event.getPrice());
	}
	
}
