package com.senzo.qettal.checkout.purchase;

import static java.util.stream.LongStream.rangeClosed;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class PurchaseItemConverter {

	public Stream<PurchaseItem> convert(PurchaseItemDTO purchaseItemDTO, PurchaseEventDTO event, Purchase purchase) {
		Long quantity = purchaseItemDTO.getQuantity();
		return rangeClosed(1l, quantity)
        		.mapToObj(i -> new PurchaseItem(event.getPrice(), purchase));
	}
	
}
