package com.senzo.qettal.checkout.purchase;

import static org.springframework.http.HttpMethod.PUT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PurchaseItemConverter {
	
	@Value("${url.checkoutEvents}")
	private String checkoutEventsUrl;

	public PurchaseItem convert(PurchaseItemDTO purchaseItemDTO, Purchase purchase) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			CheckoutToEventDTO checkoutDto = new CheckoutToEventDTO(purchaseItemDTO.getQuantity());
			ResponseEntity<EventDTO> response = restTemplate.exchange(checkoutEventsUrl + "/" + purchaseItemDTO.getEventId(), PUT, new HttpEntity<CheckoutToEventDTO>(checkoutDto), EventDTO.class);
			EventDTO event = response.getBody();
			return new PurchaseItem(event.getName(), event.getDescription(), purchaseItemDTO.getQuantity(), event.getPrice(), purchase);
		} catch (HttpClientErrorException e)   {
			throw new EventNotAvailableException(e);
		}
	}
	
}
