package com.senzo.qettal.checkout.purchase;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.ticket.TicketType;

@Component
public class PurchaseConverter {
	
	public PurchaseDTO convert(Purchase purchase){
		Map<TicketType, List<PurchaseItem>> itemsByType = purchase.getItems().stream().collect(groupingBy(PurchaseItem::getType));
		List<PurchaseItemDTO> items = itemsByType.entrySet()
				.stream()
				.map(e -> new PurchaseItemDTO(Long.valueOf(e.getValue().size()), e.getKey()))
				.collect(Collectors.toList());
		
		return new PurchaseDTO(items, purchase.getId());
	}
}
